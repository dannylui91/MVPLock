package nyc.c4q.dannylui.mvplock;

/**
 * Created by dannylui on 1/14/17.
 */

/*
Model of a lock using a singleton
 */
public class LockModel implements Lock.Model{
    private static LockModel instance;
    private String combination;

    private LockModel() {

    }

    public static LockModel getInstance() {
        if (instance == null) {
            instance = new LockModel();
        }
        return instance;
    }

    @Override
    public void setLockCombination(String combination) {
        this.combination = combination;
    }

    @Override
    public String getLockCombination() {
        return combination;
    }

    @Override
    public boolean evaluateCombination(String combination) {
        if (this.combination != null)
            return this.combination.equals(combination);
        else
            return false;
    }
}
