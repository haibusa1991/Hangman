package com.utils;

import com.strings.Filenames;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Utils {
    public static void centerFrame(JFrame frame) {
        Dimension d = frame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (screenSize.width / 2 - d.getWidth() / 2), (int) (screenSize.height / 2 - d.getHeight() / 2));
    }

    public static void centerFrame(JDialog dialog) {
        Dimension d = dialog.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        dialog.setLocation((int) (screenSize.width / 2 - d.getWidth() / 2), (int) (screenSize.height / 2 - d.getHeight() / 2));
    }

    public static void addUnderline(JLabel label) {
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
    }

    public static void removeUnderline(JLabel label) {
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, null);
        label.setFont(font.deriveFont(attributes));
    }

    public static BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
