package hangman.gfxController;

import hangman.util.FileHandler;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GfxController {
    private static GfxController instance = null;
    private List<BufferedImage> images = null;

    private GfxController() {
        loadImages();
    }

    public static GfxController getInstance() {
        if (instance == null) {
            instance = new GfxController();
        }
        return instance;
    }

    public BufferedImage getMockImage() {
        return this.images.get(8);
    }

    private void loadImages() {
        FileHandler fileHandler = new FileHandler();
        this.images = fileHandler.loadGfx();
    }

}
