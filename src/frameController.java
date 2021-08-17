public class frameController {
    private static frameController controller = null;

    private frameController() {

    }

    public static frameController getInstance() {
        if (controller == null) {
            controller = new frameController();
        }
        return controller;
    }

    public static void closeWindow(myFrame frame){
        frame.dispose();
    }
}
