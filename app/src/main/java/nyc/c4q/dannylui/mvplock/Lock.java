package nyc.c4q.dannylui.mvplock;

/**
 * Created by dannylui on 1/14/17.
 */

public interface Lock {
    interface View {
        void onSetLockPressed();
        void onKeyPressed(char key);
        void setLockDisplay(String input);
        void onUnlocked();
        void onCombinationChanged(String combination);
    }

    interface Presenter {
        void nextKey(char key);
        void newLockCombination(String combination);
    }

    interface Model {
        String getLockCombination();
        void setLockCombination(String combination);
        boolean evaluateCombination(String combination);
    }
}
