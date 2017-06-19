package ch.cpnv.vox.meteor_destroy.Model;

/**
 * A word contains in a Vocabulary
 */

public class Word {
    private int mId;
    private String mValue1, mValue2;

    /**
     * Get the voc ID
     * @param id The id of the word
     * @param value1 The word in the first language
     * @param value2 The word in the second language (translate)
     */
    public Word(int id, String value1, String value2) {
        mId = id;
        mValue1 = value1;
        mValue2 = value2;
    }

    /**
     * Get the id of the word
     * @return The id of the word
     */
    public int getId() {
        return mId;
    }

    /**
     * Get the value of the word in the first language
     * @return The value
     */
    public String getValue1() {
        return mValue1;
    }

    /**
     * Get the value of the word in the seconde language (translate)
     * @return The value
     */
    public String getValue2() {
        return mValue2;
    }
}
