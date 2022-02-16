package com.logicController;

import com.enums.Difficulty;

import java.util.Random;

public class RandomWordmask {
    //easy difficulty
    private static final int EASY_MINIMUM_LENGTH = 3;
    private static final int EASY_MAXIMUM_LENGTH = 6;
    private static final char[] EASY_ALLOWED_LETTERS = new char[]{'а', 'е', 'о', 'и', 'т'};

    //medium difficulty
    private static final int MEDIUM_MINIMUM_LENGTH = 4;
    private static final int MEDIUM_MAXIMUM_LENGTH = 9;
    private static final char[] MEDIUM_ALLOWED_LETTERS = new char[]{'с', 'в', 'р', 'к', 'п'};

    //hard difficulty
    private static final int HARD_MINIMUM_LENGTH = 8;
    private static final int HARD_MAXIMUM_LENGTH = 13;
    private static final char[] HARD_ALLOWED_LETTERS = new char[]{'г', 'у', 'б', 'ч', 'ш', 'х', 'й'};


    public String getMask(Difficulty difficulty) {
        Random r = new Random();
        int length = 1;
        char baseLetter = 'а';

        switch (difficulty) {
            case EASY -> {
                length = r.nextInt(EASY_MAXIMUM_LENGTH - EASY_MINIMUM_LENGTH+1) + EASY_MINIMUM_LENGTH;
                baseLetter = EASY_ALLOWED_LETTERS[r.nextInt(EASY_ALLOWED_LETTERS.length)];
            }
            case MEDIUM -> {
                length = r.nextInt(MEDIUM_MAXIMUM_LENGTH - MEDIUM_MINIMUM_LENGTH+1) + MEDIUM_MINIMUM_LENGTH;
                baseLetter = MEDIUM_ALLOWED_LETTERS[r.nextInt(MEDIUM_ALLOWED_LETTERS.length)];
            }
            case HARD -> {
                length = r.nextInt(HARD_MAXIMUM_LENGTH - HARD_MINIMUM_LENGTH+1) + HARD_MINIMUM_LENGTH;
                baseLetter = HARD_ALLOWED_LETTERS[r.nextInt(HARD_ALLOWED_LETTERS.length)];
            }
        }

        int letterPos = r.nextInt(length);

        StringBuilder sb = new StringBuilder();

        sb.append(".".repeat(letterPos));
        sb.append(baseLetter);
        sb.append(".".repeat(length - sb.length()));

        return sb.toString();
    }
}
