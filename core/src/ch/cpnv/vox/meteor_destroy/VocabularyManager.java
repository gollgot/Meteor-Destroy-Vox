package ch.cpnv.vox.meteor_destroy;

import java.util.ArrayList;
import java.util.Random;

import ch.cpnv.vox.meteor_destroy.Model.Vocabulary;
import ch.cpnv.vox.meteor_destroy.Model.Word;

/**
 * Created by Loic.DESSAULES on 30.05.2017.
 */

public class VocabularyManager {

    public static Word getWordToFind(){
        ArrayList<Word> words = MeteorDestroy.mVocabulary.getWords();
        return words.get(randomNumber(0, words.size()-1));
    }

    public static String getRandomTranslateWord(){
        ArrayList<Word> words = MeteorDestroy.mVocabulary.getWords();
        return words.get(randomNumber(0, words.size()-1)).getValue2();
    }

    private static int randomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
