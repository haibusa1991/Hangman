package com.gameController;

import java.io.Serializable;

public class Word implements Serializable {
    private final String word;
    private final String description;

    public Word(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }
}


