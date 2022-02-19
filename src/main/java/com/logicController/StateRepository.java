package com.logicController;

import com.enums.Difficulty;
import com.gameController.HangmanGameState;
import com.gameController.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * StateRepository holds current state of the game, hence the name. It is responsible for
 * current word, words played, load-save game sequence.
 * <p>
 * 11.01 - this is slowly becoming a word repository
 */
public class StateRepository {
    private List<Word> easyWords;
    private List<Word> mediumWords;
    private List<Word> hardWords;


    public StateRepository() {
        initializeWordSets();
    }

    private void initializeWordSets() {
        FileHandler fh = new FileHandler();
        this.easyWords = fh.readDbFromDisk(Difficulty.EASY);
        this.mediumWords = fh.readDbFromDisk(Difficulty.MEDIUM);
        this.hardWords = fh.readDbFromDisk(Difficulty.HARD);
    }

    public void saveState(HangmanGameState state) {
        //todo implement
        System.out.println("Game saved!");
    }

    public void clearState() {
        //todo implement - clears the current saved game
        System.out.println("Game discarded!");
    }

    public void setEasyWords(List<Word> easyWords) {
        this.easyWords = easyWords;
    }

    public void setMediumWords(List<Word> mediumWords) {
        this.mediumWords = mediumWords;
    }

    public void setHardWords(List<Word> hardWords) {
        this.hardWords = hardWords;
    }

    public Word getEasyWord() {
        return easyWords.get(new Random().nextInt(easyWords.size()));
    }

    public Word getMediumWord() {
        return mediumWords.get(new Random().nextInt(mediumWords.size()));
    }

    public Word getHardWord() {
        return hardWords.get(new Random().nextInt(hardWords.size()));
    }
}
