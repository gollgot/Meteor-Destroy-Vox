package ch.cpnv.vox.meteor_destroy.Model;

/**
 * Created by Loic.DESSAULES on 29.05.2017.
 */

public class Word {
    private int mId;
    private String mValue1, mValue2;

    public Word(int id, String value1, String value2) {
        mId = id;
        mValue1 = value1;
        mValue2 = value2;
    }

    public int getId() {
        return mId;
    }

    public String getValue1() {
        return mValue1;
    }

    public String getValue2() {
        return mValue2;
    }
}
