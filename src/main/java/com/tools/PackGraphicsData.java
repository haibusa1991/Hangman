package com.tools;

import com.utils.Utils;
import com.logicController.FileHandler;
import com.logicController.GraphicsPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;


public class PackGraphicsData implements Serializable {

    private final int NUMBER_OF_FRAMES = 14;
    private final String PNG_SOURCE_PATH = "D:\\Repos\\Hangman\\gfx sources\\";

    private GraphicsPackage loadSourceImagesFromDisk() {
        try {
            GraphicsPackage pack = new GraphicsPackage();
            for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
                String filename = PNG_SOURCE_PATH + "hanged" + i + ".png";
                byte[] image = Files.readAllBytes(Paths.get(filename));
                pack.add(i, image);
            }
            return pack;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new GraphicsPackage();
        }
    }

    private void savePackedImagesToDisk(GraphicsPackage pack) {
        try {
            FileOutputStream fos = new FileOutputStream(Utils.getGraphicsPath());
            ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
            oos.writeObject(pack);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<BufferedImage> loadGfx(GraphicsPackage graphics) {
        List<BufferedImage> output = new ArrayList<>();
        for (Map.Entry<Integer, byte[]> byteImage : graphics.getContents().entrySet()) {
            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteImage.getValue()));
                output.add(image);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }

    public static void main(String[] args) {
        PackGraphicsData packGraphicsData = new PackGraphicsData();
        GraphicsPackage gfxPack = packGraphicsData.loadSourceImagesFromDisk();
        packGraphicsData.savePackedImagesToDisk(gfxPack);
        FileHandler fileHandler = new FileHandler();


        try {
            GraphicsPackage graphics = fileHandler.readGraphicsFromDiskDebug();
            List<BufferedImage> images = packGraphicsData.loadGfx(graphics);

            JFrame frame = new JFrame("Image");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JLabel currentImage = new JLabel(new ImageIcon(images.get(0)));
            frame.add(currentImage);
            frame.setSize(500, 800);
            frame.setResizable(false);
            Utils.centerFrame(frame);
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


