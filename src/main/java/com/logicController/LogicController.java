package com.logicController;

import com.game.frameController.FrameController;
import com.game.frameController.GameFrame;
import com.game.frameController.MenuFrame;
import com.strings.Urls;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LogicController {
    private static LogicController instance = null;

    private StateRepository stateRepository;
    private SettingsManager settingsManager;
    private FrameController frameController;

    private LogicController() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        stateRepository = new StateRepository();
        settingsManager = new SettingsManager();
        frameController = new FrameController();
    }


    public static LogicController getInstance() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = new LogicController();
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

    public void openGithub() {
        try {
            Desktop.getDesktop().browse(new URL(Urls.GITHUB_URL).toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showMenu(){
        this.frameController.showFrame(new MenuFrame());
    }

    public static void terminateApp() {
        System.exit(0);
    }

}
