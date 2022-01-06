package com.logicController;

/**
 * StateRepository holds current state of the game, hence the name. It is responsible for
 * settings state (load-save sequence included), current word, words played, load-save game sequence.
 */
public class StateRepository {
    private SettingsManager settingsManager = null;


    public StateRepository() {
        settingsManager = new SettingsManager();
    }

}
