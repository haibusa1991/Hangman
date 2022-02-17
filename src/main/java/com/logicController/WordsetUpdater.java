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
        OnlineWordsFetcher owf;
        List<Word> words = new ArrayList<>();

        while (words.isEmpty()) {
            String wordmask = new RandomWordmask().getMask(this.difficulty);
            owf = new OnlineWordsFetcher(wordmask);
            Thread fetcher = new Thread(owf);

            fetcher.start();
            while (!fetcher.isAlive()) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            words = owf.getWords();
        }

        switch (this.difficulty) {
            case EASY -> this.stateRepository.setEasyWords(words);
            case MEDIUM -> this.stateRepository.setMediumWords(words);
            case HARD -> this.stateRepository.setHardWords(words);
        }


    }
}
