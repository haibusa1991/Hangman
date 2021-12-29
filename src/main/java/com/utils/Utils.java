package com.utils;


import com.main.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Utils {
    public static void setCentered(JFrame frame) {
        Dimension d = frame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (screenSize.width / 2 - d.getWidth() / 2), (int) (screenSize.height / 2 - d.getHeight() / 2));
    }

    public static void setCentered(JDialog dialog) {
        Dimension d = dialog.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int) (screenSize.width / 2 - d.getWidth() / 2), (int) (screenSize.height / 2 - d.getHeight() / 2));
    }

    public static String getResourcesPath() {
//        jar:file:/D:/Repos/Hangman/bin/artifacts/Hangman_jar/Hangman.jar!/com.main/Hangman.class

        int SYMBOLS_TO_TRIM_FROM_START=6;  //       "file:/"
        int SYMBOLS_TO_TRIM_FROM_THE_END=18; //     "com.main/Hangman.class"
        String fullPath = Hangman.class.getResource("Hangman.class").toString();
        fullPath = fullPath.substring(SYMBOLS_TO_TRIM_FROM_START,fullPath.length()-SYMBOLS_TO_TRIM_FROM_THE_END);
        return fullPath + "resources";
    }

    public static String getWindowIconPath() {
        return getResourcesPath() + "/icon.png";
    }

    public static String getGraphicsPath() {
        return getResourcesPath() + "/gfx.dat";
    }

    public static String getArtifactPath(){
        String fullPath = Hangman.class.getResource("Hangman.class").toString();
//        jar:file:/D:/Repos/Hangman/bin/artifacts/Hangman_jar/Hangman.jar!/com.main/Hangman.class
        int SYMBOLS_TO_TRIM_FROM_START=10;  //       "jar:file:/"
        String f = Hangman.class.getProtectionDomain().getCodeSource().toString();
        return null;
    }

    public static void setUnderlined(JLabel label) {
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
    }

    public static void setPlain(JLabel label) {
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, null);
        label.setFont(font.deriveFont(attributes));
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
