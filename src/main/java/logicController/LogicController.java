package logicController;

import gfxController.GfxController;
import io.FileHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class LogicController {
    private static LogicController instance = null;

    private LogicController() {

    }

    public static LogicController getInstance() {
        if (instance == null) {
            instance = new LogicController();
        }
        return instance;
    }

    public Settings readSettingsFromDisk() {
        FileHandler fh = new FileHandler();
        try {
            return fh.readSettingsFromDisk();
        } catch (Exception ex) {
            return new Settings();
        }
    }

    public void saveSettingsToDisk(Settings settings) {
        FileHandler fh = new FileHandler();
        fh.writeSettingsToDisk(settings);
    }

    public void openFunlandSite() {
        try {
            Desktop.getDesktop().browse(new URL("http://funland.bg").toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void openGithub() {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/haibusa1991").toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void terminateApp() {
        System.exit(0);
    }

//    public void createEmptySettings() {
//        FileHandler fh = new FileHandler();
//        fh.writeToDisk(new Settings());
//    }

    public BufferedImage readMockImage() {
        GfxController gfx = GfxController.getInstance();
        return gfx.getMockImage();
    }


}
