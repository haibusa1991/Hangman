package com.game.frameController;

import com.logicController.LogicController;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class FrameController {
    private static FrameController instance = null;

    private FrameController() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {
        initialize();
    }

    private void initialize() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    }

    public void showMenuFrame() {
        MenuFrame.getInstance().showFrame();
    }


    public static FrameController getInstance() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {
        if (instance == null) {
            instance = new FrameController();
        }
        return instance;
    }

    public void showAboutDialog() {
        AboutDialog ad = AboutDialog.getInstance();
        ad.showDialog();
    }

    private void isolateGameFrame() {
        AboutDialog ad = AboutDialog.getInstance();
        ad.dispose();

        MenuFrame mf = MenuFrame.getInstance();
        mf.dispose();

        GameFrame gf = GameFrame.getInstance();
        gf.showFrame();
    }

    public void closeAboutDialog() {
        AboutDialog ad = AboutDialog.getInstance();
        ad.dispose();
    }

    public void closeGameFrame() {
        GameFrame.getInstance().close();
    }

    public void showSettingsDialog() {
        SettingsDialog sd = SettingsDialog.getInstance();
        sd.showDialog();
    }

    public void throwError(String message) {
        new ErrorDialog(message);
        LogicController.getInstance().terminateApp(); //todo comment out if necessary
    }

    public void showGameFrame() {
        isolateGameFrame();

        LogicController lc = LogicController.getInstance();
        BufferedImage bi = lc.readMockImage();

        GameFrame gf = GameFrame.getInstance();
        gf.setMockImage(bi);
        gf.showFrame();
    }

}
