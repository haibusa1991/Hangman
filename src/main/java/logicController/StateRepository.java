package logicController;

/**
 * StateRepository holds current state of the game, hence the name. It is responsible for
 * settings state (load-save sequence included), current word, words played, load-save game sequence
 */
public class StateRepository {
    private static StateRepository instance = null;
    private Settings settings;



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
        SettingsManager sm = new SettingsManager();

    }

    public Difficulty getDifficulty() {
        return this.settings.difficulty;
    }

        public boolean isOnlineMode() {
        return this.settings.isOnline;
    }

        public boolean doesSaveOnExit() {
        return this.settings.doesSaveOnExit;
    }


}
