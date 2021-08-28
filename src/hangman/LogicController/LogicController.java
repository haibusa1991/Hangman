package hangman.LogicController;

import hangman.frames.SettingsDialog;

import java.util.ArrayList;
import java.util.List;

public class LogicController {
    private static LogicController instance = null;

    private LogicController() {
    }

    public static LogicController getInstance() {
        if (instance == null) {
            instance = new LogicController();
            return instance;
        }
        return instance;
    }

    public void saveSettings() {
        SettingsDialog sd = SettingsDialog.getInstance();
        Settings settings = new Settings();
        settings.isOnline = sd.getCheckBox_onlineMode();
        List<Boolean> difficultiesStates = new ArrayList<>();
        difficultiesStates.add(sd.getRadioButton_easyDifficulty());
        difficultiesStates.add(sd.getRadioButton_mediumDifficulty());
        difficultiesStates.add(sd.getRadioButton_hardDifficulty());
        settings.difficulty = difficultiesStates;
        settings.doesSaveOnExit = sd.getCheckBox_saveOnExit();
        SettingsFileHandler sfh = new SettingsFileHandler();
        sfh.saveSettings(settings);
    }

    public void loadSettings() {
        SettingsDialog sd = SettingsDialog.getInstance();
        SettingsFileHandler sfh = new SettingsFileHandler();
        Settings settings = sfh.loadSettings();
        sd.setCheckBox_onlineMode(settings.isOnline);
        sd.setRadioButton_easyDifficulty(settings.difficulty.get(0));
        sd.setRadioButton_mediumDifficulty(settings.difficulty.get(1));
        sd.setRadioButton_hardDifficulty(settings.difficulty.get(2));
        sd.setCheckBox_saveOnExit(settings.doesSaveOnExit);
    }
}
