package com.main;

import com.enums.Difficulty;
import com.gameController.Word;
import com.logicController.RandomWordmask;
import com.logicController.StateRepository;

import java.util.ArrayList;
import java.util.List;

public class DummyUpdater implements Runnable {
    private final StateRepository stateRepository;
    private final Difficulty difficulty;


    public DummyUpdater(StateRepository stateRepository, Difficulty difficulty) {
        this.stateRepository = stateRepository;
        this.difficulty = difficulty;
    }

    @Override
    public void run() {
        System.out.println("starded dummy updater");
        DummyDownloader dd;
        List<Word> words = new ArrayList<>();

        while (words.isEmpty()) {
            String wordmask = new RandomWordmask().getMask(this.difficulty);
            dd = new DummyDownloader(wordmask);
            dd.fetchPairs();
            words = dd.getWords();
        }

        switch (this.difficulty) {
            case EASY -> this.stateRepository.setEasyWords(words);
            case MEDIUM -> this.stateRepository.setMediumWords(words);
            case HARD -> this.stateRepository.setHardWords(words);
        }
    }
}


