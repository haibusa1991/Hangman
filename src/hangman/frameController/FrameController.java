package hangman.frameController;

import hangman.logicController.LogicController;
import hangman.logicController.Settings;

import javax.swing.*;

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

//    public static void getRandomWord() throws IOException, InterruptedException {
//        HangmanFrame frame = HangmanFrame.getInstance();
//        WordGenerator wg = WordGenerator.getInstance();
//        frame.setLabel_randomWord(wg.getRandomWord());
//    }

    public void closeOtherFrames() {
        AboutDialog ad = AboutDialog.getInstance();
        ad.dispose();
    }

//    public static void updateOnlineStatus(boolean currentStatus) {
//        HangmanFrame.getInstance().setLabel_modeText("Current internet status is: " + currentStatus);
//    }

    public void showSettingsDialog() {
        SettingsDialog sd = SettingsDialog.getInstance();
        LogicController lc = LogicController.getInstance();
        Settings s = lc.readSettingsFromDisk();
        sd.setSettings(s);
        sd.showDialog();
    }

    public void throwError(String message) {
        JDialog errorDialog = new errorDialog(message);
    }
}
