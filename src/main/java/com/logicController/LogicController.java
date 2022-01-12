package com.logicController;

import com.database.OnlineWordsFetcher;
import com.dialogCommands.ShowSaveGameDialogCommand;
import com.dialogCommands.ShowWarningDialogCommand;
import com.frameCommands.*;
import com.dialogs.ErrorDialog;
import com.frames.GameFrame;
import com.gameController.GameController;
import com.gameController.HangmanGameState;
import com.gameController.Letter;
import com.gameController.Word;
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
    private GameController gameController = null;

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

        initializeStateRepository();

    }

    private void initializeStateRepository() {
        //todo implement properly
        // online fetcher should fetch 3 list of words (easy, medium and hard) and populate the repo
        // if online mode is off should query the local db and get the words from it
        // word mask should be dynamically generated and words updated after several games (like 3 or so)
        OnlineWordsFetcher owfE = new OnlineWordsFetcher("..а..");
        OnlineWordsFetcher owfM = new OnlineWordsFetcher("...р....");
        OnlineWordsFetcher owfH = new OnlineWordsFetcher("...й.");
        this.stateRepository.setEasyWords(owfE.getWords());
        this.stateRepository.setMediumWords(owfM.getWords());
        this.stateRepository.setHardWords(owfH.getWords());

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

    public void gameFrameLetterButtonClick(Letter letter) {
        this.gameController.letterClick(letter);
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
        Integer result = closeGameFrameSequence();
        if (result == JOptionPane.CANCEL_OPTION) { //  user has pressed cancel so do nothing
            return;
        }
        terminateApp();
    }

    public void gameFrameButtonClickMenu() {
        Integer result = closeGameFrameSequence();
        if (result == JOptionPane.CANCEL_OPTION) { //  user has pressed cancel so do nothing
            return;
        }
        this.windowController.hideFrame(new HideGameFrameCommand());
        this.gameController = null;
        this.windowController.showFrame(new ShowMenuFrameCommand());
    }

    private Integer closeGameFrameSequence() {
        boolean doesShowSaveDialog = this.settingsManager.getSettings().doesSaveOnExit;
        if (doesShowSaveDialog) {
            saveGameState();
            return 0;
        }

        Integer result = this.windowController.showDialog(new ShowSaveGameDialogCommand());
        switch (result) {
            case JOptionPane.YES_OPTION -> saveGameState();
            case JOptionPane.NO_OPTION -> clearGameState();
        }
        return result;
    }

    public void gameFrameButtonClickNewGame() {
        gameController.startNewGame(this.settingsManager.getSettings().difficulty);
    }

    public void menuFrameButtonClickNewGame() {
        this.windowController.hideFrame(new HideMenuFrameCommand());
        this.windowController.showFrame(new ShowGameFrameCommand());
        GameFrame gameFrame = this.windowController.getGameFrame();
        gameController = new GameController(gameFrame, this.graphicsManager);
        gameFrameButtonClickNewGame();
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

    private void saveGameState() {
        this.stateRepository.saveState(new HangmanGameState());
    }

    private void clearGameState() {
        this.stateRepository.clearState();
    }

    public Word getEasyWord() {
        return this.stateRepository.getEasyWord();
    }

    public Word getMediumWord() {
        return this.stateRepository.getMediumWord();
    }

    public Word getHardWord() {
        return this.stateRepository.getHardWord();
    }
}

