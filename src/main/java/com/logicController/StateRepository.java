package com.logicController;

/**
 * StateRepository holds current state of the game, hence the name. It is responsible for
 * settings state (load-save sequence included), current word, words played, load-save game sequence.
 * It also holds all the required graphics objects.
 */
public class StateRepository {
    private static StateRepository instance = null;
    private SettingsManager settingsManager = null;


    private StateRepository() {
        initializeState();
    }

    public static StateRepository getInstance() {
        if (instance == null) {
            instance = new StateRepository();
        }
        return instance;
    }

    private void initializeState() {
        //todo initialize state
        settingsManager = new SettingsManager();
    }


}
