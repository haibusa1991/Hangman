import java.io.IOException;

public class FrameController {
    private static FrameController controller = null;
    private static FrameUpdater updater = new FrameUpdater();

    private FrameController() {

        updater.start();
    }

    public static FrameController getInstance() {
        if (controller == null) {
            controller = new FrameController();
        }
        return controller;
    }

    public static void closeWindow() {
        MyFrame frame = MyFrame.getInstance();
        updater.interrupt();
        frame.dispose();
    }

    public static void getRandomWord() throws IOException, InterruptedException {
        MyFrame frame = MyFrame.getInstance();
        WordGenerator wg = WordGenerator.getInstance();
        frame.setLabel_randomWord(wg.getRandomWord());
    }

    public static void updateOnlineStatus(boolean currentStatus) {
        MyFrame.getInstance().setLabel_modeText("Current internet status is: " + currentStatus);
    }
}
