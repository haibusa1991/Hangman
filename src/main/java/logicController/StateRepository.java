package logicController;

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
        this.settings = LogicController.getInstance().readSettingsFromDisk();
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
