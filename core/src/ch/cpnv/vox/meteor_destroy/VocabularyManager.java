package ch.cpnv.vox.meteor_destroy;

import java.util.ArrayList;
import java.util.Random;

import ch.cpnv.vox.meteor_destroy.Model.Word;

/**
 * Class which manage the Vocabulary
 */

public class VocabularyManager {

    /**
     * Get a random word that the player have to find
     * @return a Word
     */
    public static Word getWordToFind(){
        ArrayList<Word> words = MeteorDestroy.vocabulary.getWords();
        return words.get(randomNumber(0, words.size()-1));
    }

    /**
     * Get a translate value of a random word, will display on a meteor
     * @return a translate word's value
     */
    public static String getRandomTranslateWord(){
        ArrayList<Word> words = MeteorDestroy.vocabulary.getWords();
        return words.get(randomNumber(0, words.size()-1)).getValue2();
    }

    /**
     * generate a random number min and max included
     * @param min The minimum value you want
     * @param max The maximum value you want
     * @return A random number
     */
    private static int randomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
