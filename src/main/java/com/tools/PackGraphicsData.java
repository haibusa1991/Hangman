package com.tools;

import com.utils.Utils;
import com.io.FileHandler;
import com.gfxController.GfxPack;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.DeflaterOutputStream;


public class PackGraphicsData implements Serializable {

    private final int NUMBER_OF_FRAMES = 14;
    private final String PNG_SOURCE_PATH = "C:\\Java\\Hangman\\gfx sources\\";

    private GfxPack loadSourceImagesFromDisk() {
        try {
            GfxPack pack = new GfxPack();
            for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
                String filename = PNG_SOURCE_PATH + "hanged" + i + ".png";
                byte[] image = Files.readAllBytes(Paths.get(filename));
                pack.add(i, image);
            }
            return pack;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GfxPack();
        }
    }

    private void savePackedImagesToDisk(GfxPack pack) {
        try {
            String target = Utils.getGraphicsPath();
            FileOutputStream fos = new FileOutputStream(Utils.getGraphicsPath());
            ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
            oos.writeObject(pack);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PackGraphicsData packGraphicsData = new PackGraphicsData();
        GfxPack gfxPack = packGraphicsData.loadSourceImagesFromDisk();
        packGraphicsData.savePackedImagesToDisk(gfxPack);
        FileHandler fileHandler= new FileHandler();
        List<BufferedImage> images = fileHandler.loadGfx();
        try {
            JFrame frame = new JFrame("Image");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JLabel currentImage = new JLabel(new ImageIcon(images.get(0)));
            frame.add(currentImage);
            frame.setSize(500, 800);
            frame.setResizable(false);
            Utils.setCentered(frame);
            frame.setVisible(true);
            for (BufferedImage image : images) {
                currentImage.setIcon(new ImageIcon(image));
                frame.repaint();
                Thread.sleep(250);
            }
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}


