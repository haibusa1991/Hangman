package com.logicController;

import com.game.frameController.AboutDialog;
import com.game.frameController.FrameController;
import com.game.frameController.MenuFrame;
import com.game.frameController.SettingsDialog;
import com.strings.Urls;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class LogicController {
    private static LogicController instance = null;

    private StateRepository stateRepository;
    private SettingsManager settingsManager;
    private FrameController frameController;
    private GraphicsManager graphicsManager;

    private LogicController() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        stateRepository = new StateRepository();
        settingsManager = new SettingsManager();
        frameController = new FrameController();
        graphicsManager = new GraphicsManager();
    }

    public static LogicController getInstance() {
        if (instance == null) {
            try {
                instance = new LogicController();
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

    public void showMenu() {
        this.frameController.showFrame(new MenuFrame());
    }

    public static void terminateApp() {
        System.exit(0);
    }

    public void letterButtonPress(String letter) {
        //todo implement functionality what happens when user presses a letter in game window
        new InfoDialog(String.format("You pressed the %s letter!", letter));
    }

    public void aboutDialogLabelClick() {
        openGithubLink();
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

    public void menuButtonClickNewGame() {
        //todo implement
    }

    public void menuButtonClickContinueGame() {
        //todo implement
    }

    public void menuButtonClickSettings() {
        //todo implement
    }

    public void menuButtonClickAbout() {
        //todo implement
    }

    public void menuButtonClickExit() {
        terminateApp();
    }

    public void menuLabelClickFunland() {
        openFunlandSite();
    }

    public void settingsButtonClickSave(SettingsDialog settingsDialog) {
        //todo implement - should update state of state manager

    }
}
