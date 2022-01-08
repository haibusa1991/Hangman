package com.logicController;

import com.commands.*;
import com.dialogs.ErrorDialog;
import com.dialogs.InfoDialog;
import com.dialogs.SaveGameConfirmationDialog;
import com.frames.SettingsFrame;
import com.strings.Urls;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class LogicController {
    private static LogicController instance = null;

    private StateRepository stateRepository;
    private SettingsManager settingsManager;
    private FrameController frameController;
    private GraphicsManager graphicsManager;

    private LogicController() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            IOException {
        stateRepository = new StateRepository();
        settingsManager = new SettingsManager();
        frameController = new FrameController();
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
        this.frameController.showFrame(new ShowMenuFrameCommand());
    }

    public static void terminateApp() {
        System.exit(0);
    }

    public void letterButtonPress(String letter) {
        //todo implement functionality what happens when user presses a letter in game window
        new InfoDialog(String.format("You pressed the %s letter!", letter));
    }

    public void aboutFrameLabelClick() {
        openGithubLink();
    }

    public void aboutFrameIsClosed() {
        this.frameController.hideFrame(new HideAboutFrameCommand());
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

    public void menuFrameGainsFocus() {
        aboutFrameIsClosed();
    }

    public void menuFrameButtonClickNewGame() {
        //todo implement
        new InfoDialog("you clicked New game.");
    }

    public void menuFrameButtonClickContinueGame() {
        //todo implement
    }

    public void menuFrameButtonClickSettings() {
        this.frameController.showFrame(new ShowSettingFrameCommand());
    }

    public void menuFrameButtonClickAbout() {
        this.frameController.showFrame(new ShowAboutFrameCommand());
    }

    public void menuFrameButtonClickExit() {
        terminateApp();
    }

    public void menuFrameLabelClickFunland() {
        openFunlandSite();
    }

    public void settingsFrameButtonClickSave(Settings settings) {
        System.out.println("settingsFrameButtonClickSave");
        this.settingsManager.setSettings(settings);
        settingsFrameIsClosed();
    }

    public void settingsFrameIsClosed() {
        this.frameController.hideFrame(new HideSettingsFrameCommand());
        System.out.println("settingsFrameIsClosed");
    }

    public Settings getSettings() {
        return this.settingsManager.getSettings();
    }
}

