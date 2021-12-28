package logicController;

import io.FileHandler;

import java.io.IOException;

public class SettingsManager {
    private Settings settings;

    public SettingsManager() {
        readSettingsFromDisk();
    }

    private void readSettingsFromDisk() {
        FileHandler fh = new FileHandler();
        try {
            this.settings = fh.readSettingsFromDisk();
        } catch (Exception e) {
            this.settings = new Settings();
        }
    }

    private void saveSettingsToDisk(Settings settings) throws IOException {
        FileHandler fh = new FileHandler();
        fh.writeSettingsToDisk(settings);
    }

    public Difficulty getDifficulty() {
        return this.settings.difficulty;
    }

    public boolean getIsOnline() {
        return this.settings.isOnline;
    }

    public boolean getDoesSaveOnExit() {
        return this.settings.doesSaveOnExit;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.settings.difficulty = difficulty;
    }

    public void setIsOnline(boolean value) {
        this.settings.isOnline = value;
    }

    public void setDoesSaveOnExit(boolean value) {
        this.settings.doesSaveOnExit = value;
    }
}

