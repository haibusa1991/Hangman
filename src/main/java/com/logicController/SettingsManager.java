package com.logicController;


import java.io.IOException;

public class SettingsManager {
    private Settings settings;

    public SettingsManager() {
        readSettingsFromDisk();
    }

    private void readSettingsFromDisk() {
        try {
            this.settings = new FileHandler().readSettingsFromDisk();
        } catch (Exception e) {
            this.settings = new Settings();
        }
    }

    public void saveSettingsToDisk(Settings settings) throws IOException {
        new FileHandler().writeSettingsToDisk(settings);
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}

