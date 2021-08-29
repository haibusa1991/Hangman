package hangman.LogicController;

import hangman.frames.SettingsDialog;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LogicController {
    private static LogicController instance = null;
    private Settings currentSettings = null;

    private LogicController() {
        SettingsDialog.getInstance();
        loadSettings();
    }

    public static LogicController getInstance() {
        if (instance == null) {
            instance = new LogicController();
            return instance;
        }
        return instance;
    }

    public void saveSettings() {
        SettingsDialog settingsDialog = SettingsDialog.getInstance();
        Settings settings = new Settings();
        settings.isOnline = settingsDialog.getCheckBox_onlineMode();
        List<Boolean> difficultiesStates = new ArrayList<>();
        difficultiesStates.add(settingsDialog.getRadioButton_easyDifficulty());
        difficultiesStates.add(settingsDialog.getRadioButton_mediumDifficulty());
        difficultiesStates.add(settingsDialog.getRadioButton_hardDifficulty());
        settings.difficulty = difficultiesStates;
        settings.doesSaveOnExit = settingsDialog.getCheckBox_saveOnExit();
        FileHandler fileHandler = new FileHandler();
        fileHandler.saveSettings(settings);
        currentSettings = settings;
    }

    public void loadSettings() {
        if (currentSettings == null) {
            FileHandler fileHandler = new FileHandler();
            currentSettings = fileHandler.loadSettings();
        } else {
            SettingsDialog settingsDialog = SettingsDialog.getInstance();
            settingsDialog.setCheckBox_onlineMode(currentSettings.isOnline);
            settingsDialog.setRadioButton_easyDifficulty(currentSettings.difficulty.get(0));
            settingsDialog.setRadioButton_mediumDifficulty(currentSettings.difficulty.get(1));
            settingsDialog.setRadioButton_hardDifficulty(currentSettings.difficulty.get(2));
            settingsDialog.setCheckBox_saveOnExit(currentSettings.doesSaveOnExit);
        }
    }

    public void openFunlandSite() {
        try {
            Desktop.getDesktop().browse(new URL("http://funland.bg").toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openGithub() {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/haibusa1991").toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
