package hangman;

import hangman.frames.AboutDialog;
import hangman.frames.HangmanFrame;
import hangman.frames.MenuFrame;

import javax.swing.*;
import java.io.IOException;

public class FrameController {
    private static FrameController controller = null;
//    private static FrameUpdater updater = new FrameUpdater();

    private FrameController() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

//        updater.start();
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        showMenuFrame();
    }

    public void terminateApp() {
        System.exit(0);
    }

    public static FrameController getInstance() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (controller == null) {
            controller = new FrameController();
        }
        return controller;
    }
//
//    public static void closeWindow() {
//        HangmanFrame frame = HangmanFrame.getInstance();
//        updater.interrupt();
//        frame.dispose();
//    }

    public void showAboutDialog() throws InterruptedException {
        AboutDialog.getInstance().showDialog();
    }

    private void showMenuFrame() {
        MenuFrame.getInstance();
    }

    public static void getRandomWord() throws IOException, InterruptedException {
        HangmanFrame frame = HangmanFrame.getInstance();
        WordGenerator wg = WordGenerator.getInstance();
        frame.setLabel_randomWord(wg.getRandomWord());
    }

    public static void updateOnlineStatus(boolean currentStatus) {
        HangmanFrame.getInstance().setLabel_modeText("Current internet status is: " + currentStatus);
    }
}
