package com.logicController;

import com.enums.Difficulty;
import com.gameController.Word;

import java.util.ArrayList;
import java.util.List;

public class WordsetUpdater implements Runnable {
    private final StateRepository stateRepository;
    private final Difficulty difficulty;


    public WordsetUpdater(StateRepository stateRepository, Difficulty difficulty) {
        this.stateRepository = stateRepository;
        this.difficulty = difficulty;
    }

    @Override
    public void run() {
        String wordmask = new RandomWordmask().getMask(this.difficulty);
        WordsFetcher wf = new WordsFetcher(wordmask);

        wf.fetchPairs();
        List<Word> words = wf.getWords();

        while (words.isEmpty()) {
            wf.setWordmask(new RandomWordmask().getMask(this.difficulty));
            wf.fetchPairs();
            words = wf.getWords();
        }

        switch (this.difficulty) {
            case EASY -> this.stateRepository.setEasyWords(words);
            case MEDIUM -> this.stateRepository.setMediumWords(words);
            case HARD -> this.stateRepository.setHardWords(words);
        }


    }
}
