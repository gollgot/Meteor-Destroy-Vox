package ch.cpnv.vox.meteor_destroy.Model;

import java.util.ArrayList;

/**
 * Created by Loic.DESSAULES on 29.05.2017.
 */

public class Vocabulary {
    private int mId;
    private String mTitle;
    private ArrayList<Word> mWords;

    public int getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public ArrayList<Word> getWords() {
        return mWords;
    }
}
