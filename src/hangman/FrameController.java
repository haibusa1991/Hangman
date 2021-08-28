package hangman;

import hangman.LogicController.LogicController;
import hangman.LogicController.SettingsFileHandler;
import hangman.frames.AboutDialog;
import hangman.frames.HangmanFrame;
import hangman.frames.MenuFrame;
import hangman.frames.SettingsDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class FrameController {
    private static FrameController instance = null;

    private FrameController() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        MenuFrame.getInstance();
    }

    public void terminateApp() {
        System.exit(0);
    }

    public static FrameController getInstance() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = new FrameController();
        }
        return instance;
    }

    public void showAboutDialog() {
        AboutDialog.getInstance().showAboutDialog();
    }

    public static void getRandomWord() throws IOException, InterruptedException {
        HangmanFrame frame = HangmanFrame.getInstance();
        WordGenerator wg = WordGenerator.getInstance();
        frame.setLabel_randomWord(wg.getRandomWord());
    }

    public static void closeOtherFrames() {
        AboutDialog.getInstance().dispose(); //close about
        //close settings

    }

    public static void updateOnlineStatus(boolean currentStatus) {
        HangmanFrame.getInstance().setLabel_modeText("Current internet status is: " + currentStatus);
    }

    public static void openFunlandSite() {
        try {
            Desktop.getDesktop().browse(new URL("http://funland.bg").toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void openGithub() {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/haibusa1991").toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showSettingsDialog(){
        LogicController.getInstance().loadSettings();
        SettingsDialog.getInstance().showSettingsDialog();

    }
}
