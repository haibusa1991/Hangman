package com.logicController;

import com.enums.Difficulty;
import com.interfaces.HangmanState;

import java.io.Serializable;

public class Settings implements Serializable,
        HangmanState {
    public Difficulty difficulty = Difficulty.MEDIUM;
    public boolean isOnline = true;
    public boolean doesSaveOnExit = true;
}