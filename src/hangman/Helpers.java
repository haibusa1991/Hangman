package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Objects;

public class Helpers {
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
        StringBuilder classPath = new StringBuilder((Objects.requireNonNull(Hangman.class.getResource("Hangman.class")).toString()));
        classPath.replace(0, 6, "");
        classPath.replace(classPath.lastIndexOf("/"), classPath.length(), "");
        classPath.append("/resources");
        while (classPath.indexOf("/") != -1) {
            classPath.replace(classPath.indexOf("/"), classPath.indexOf("/") + 1, "\\");
        }
        return classPath.toString();
    }

    public static String getWindowIconPath() {
        return getResourcesPath() + "\\icons\\window.png";
    }

    public static String getHelpIconPath() {
        return getResourcesPath() + "\\icons\\help15x15.png";
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

}
