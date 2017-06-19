package ch.cpnv.vox.meteor_destroy.Model;

import java.util.ArrayList;

/**
 * Vocabulary provide from the API and used in the GameState
 */

public class Vocabulary {
    private int mId;
    private String mTitle;
    private ArrayList<Word> mWords;

    /**
     * Get the voc ID
     * @return The id of the voc
     */
    public int getId() {
        return mId;
    }
    /**
     * Get the title of the voc
     * @return The title of the voc
     */
    public String getTitle() {
        return mTitle;
    }
    /**
     * Get all the words in the voc
     * @return all the words
     */
    public ArrayList<Word> getWords() {
        return mWords;
    }
}
