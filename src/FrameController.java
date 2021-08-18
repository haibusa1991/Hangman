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

    public static void closeWindow(MyFrame frame) {
        updater.interrupt();
        frame.dispose();
    }

    public static void updateOnlineStatus(boolean currentStatus) {
        MyFrame.getInstance().setLabel_modeText("Current internet status is: " + currentStatus);
    }
}
