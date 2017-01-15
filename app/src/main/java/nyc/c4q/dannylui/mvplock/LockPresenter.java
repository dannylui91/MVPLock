package nyc.c4q.dannylui.mvplock;

import android.text.TextUtils;

/**
 * Created by dannylui on 1/14/17.
 */

/*
Presenter that answers to inputs from the view
*/
public class LockPresenter implements Lock.Presenter {
    private Lock.View lockView;
    private LockModel lockModel;
    private String currentCombination;

    public LockPresenter(Lock.View lockView) {
        this.lockView = lockView;
        lockModel = LockModel.getInstance();
        currentCombination = "";
    }

    @Override
    public void nextKey(char key) {
        currentCombination += key;
        String formatted = String.format("%-4s", currentCombination).replace(' ', '0');
        if (currentCombination.length() != 4) {
            lockView.setLockDisplay(formatted);
        } else {
            if (lockModel.evaluateCombination(currentCombination)) {
                lockView.setLockDisplay(formatted);
                lockView.onUnlocked();
                currentCombination = "";
            } else {
                lockView.setLockDisplay("0000");
                currentCombination = "";
            }
        }
    }

    @Override
    public void newLockCombination(String combination) {
        if (combination.length() == 4 && TextUtils.isDigitsOnly(combination)) {
            lockModel.setLockCombination(combination);
            lockView.onCombinationChanged(combination);
        }
    }
}
