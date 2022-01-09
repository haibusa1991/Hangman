package com.logicController;

import com.gameController.HangmanGameState;

/**
 * StateRepository holds current state of the game, hence the name. It is responsible for
 * current word, words played, load-save game sequence.
 */
public class StateRepository {
    private SettingsManager settingsManager;

    public StateRepository() {
        settingsManager = new SettingsManager();
    }

    public void saveState(HangmanGameState state) {
        //todo implement
        System.out.println("Game saved!");
    }

    public void clearState() {
        //todo implement - clears the current saved game
        System.out.println("Game discarded!");
    }

}
