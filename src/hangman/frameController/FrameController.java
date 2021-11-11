package hangman.frameController;

import hangman.logicController.LogicController;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class FrameController {
    private static FrameController instance = null;

    private FrameController() {
        initialize();
    }

    private void initialize() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to initialize UI manager.\n" + ex.getMessage());
        }
    }

    public void showMenuFrame() {
        MenuFrame mf = MenuFrame.getInstance();
        mf.showFrame();
    }


    public static FrameController getInstance() {
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


    public void showSettingsDialog() {
        SettingsDialog sd = SettingsDialog.getInstance();
        sd.showDialog();
    }

    public void throwError(String message) {
        ErrorDialog errorDialog = new ErrorDialog(message);
    }

    public void showGameFrame(){
        isolateGameFrame();

        LogicController lc = LogicController.getInstance();
        BufferedImage bi = lc.readMockImage();

        GameFrame gf = GameFrame.getInstance();
        gf.setMockImage(bi);
        gf.showFrame();
    }

}
