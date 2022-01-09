package com.logicController;

import com.dialogCommands.ShowWarningDialogCommand;
import com.frameCommands.*;
import com.dialogs.ErrorDialog;
import com.dialogs.InfoDialog;
import com.dialogs.SaveGameConfirmationDialog;
import com.dialogs.WarningDialog;
import com.strings.Urls;
import com.strings.WarningMessages;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class LogicController {
    private static LogicController instance = null;

    private StateRepository stateRepository;
    private SettingsManager settingsManager;
    private WindowController windowController;
    private GraphicsManager graphicsManager;

    private LogicController() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            IOException {
        stateRepository = new StateRepository();
        settingsManager = new SettingsManager();
        windowController = new WindowController();
        graphicsManager = new GraphicsManager();
    }

    public static LogicController getInstance() {
        if (instance == null) {
            try {
                instance = new LogicController();
                instance.showMenu();
            } catch (Exception e) {
                new ErrorDialog(e.getMessage());
            }

        }
        return instance;
    }

    public void openFunlandSite() {
        try {
            Desktop.getDesktop().browse(new URL(Urls.FUNLAND_URL).toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void openGithubLink() {
        try {
            Desktop.getDesktop().browse(new URL(Urls.GITHUB_URL).toURI());
        } catch (Exception ignored) {
        }

    }

    private void showMenu() {
        this.windowController.showFrame(new ShowMenuFrameCommand());
    }

    public static void terminateApp() {
        System.exit(0);
    }

    public void gameFrameLetterButtonClick(String letter) {
        //todo implement functionality what happens when user presses a letter in game window
        String message = String.format("You pressed the %s letter!", letter); // it's a warning dialog, because I have no better one right now
        windowController.showDialog( new ShowWarningDialogCommand(message));
    }

    public void aboutFrameLabelClick() {
        openGithubLink();
    }

    public void aboutFrameIsClosed() {
        this.windowController.hideFrame(new HideAboutFrameCommand());
    }

    public void aboutFrameLosesFocus() {
        aboutFrameIsClosed();
    }

    public void gameFrameButtonClickExit() {
        new SaveGameConfirmationDialog(); // todo implement save game logic
        terminateApp();
    }

    public void gameFrameButtonClickMenu() {
        //todo implement go to menu
    }

    public void gameFrameButtonClickNewGame() {
        //todo implement
    }

    public void menuFrameButtonClickNewGame() {
        this.windowController.hideFrame(new HideMenuFrameCommand());
        this.windowController.showFrame(new ShowGameFrameCommand());
    }

    public void menuFrameButtonClickContinueGame() {
        //todo implement
    }

    public void menuFrameButtonClickSettings() {
        this.windowController.showFrame(new ShowSettingFrameCommand());
    }

    public void menuFrameButtonClickAbout() {
        this.windowController.showFrame(new ShowAboutFrameCommand());
    }

    public void menuFrameButtonClickExit() {
        terminateApp();
    }

    public void menuFrameLabelClickFunland() {
        openFunlandSite();
    }

    public void settingsFrameButtonClickSave(Settings settings) {
        this.settingsManager.setSettings(settings);
        try {
            this.settingsManager.saveSettingsToDisk(settings);
        } catch (IOException e) {
            this.windowController.showDialog(new ShowWarningDialogCommand(WarningMessages.SETTINGS_FRAME_UNABLE_TO_SAVE_SETTINGS_TO_DISK));
        }
        settingsFrameIsClosed();
    }

    public void settingsFrameIsClosed() {
        this.windowController.hideFrame(new HideSettingsFrameCommand());
    }

    public Settings getSettings() {
        return this.settingsManager.getSettings();
    }
}

