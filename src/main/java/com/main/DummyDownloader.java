package com.main;

import com.gameController.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DummyDownloader implements Runnable {

    private final String wordmask;
    private List<Word> words;

    public DummyDownloader(String wordmask) {
        this.wordmask = wordmask;
        words = new ArrayList<>();
    }

    private void fetchPairs(String wordMask) {
        Random r = new Random();
        int duration = (r.nextInt(5) + 3) * 1000;

        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        words.add(new Word("Dummy word","Dummy description goes here"));
    }

    public List<Word> getWords() {
        return Collections.unmodifiableList(this.words);
    }

    @Override
    public void run() {
        fetchPairs(this.wordmask);
    }

}
