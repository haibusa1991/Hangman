import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class helpers {
    public static void setCentered(JFrame frame) {
        Dimension d = frame.getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) (screenSize.width / 2 - d.getWidth() / 2), (int) (screenSize.height / 2 - d.getHeight()/2));
    }

    private static String getResourcesPath() {
        StringBuilder classPath = new StringBuilder(Objects.requireNonNull(main.class.getClassLoader().getResource("main.class")).toString());
        classPath.replace(0, 6, "");
        classPath.replace(classPath.lastIndexOf("/"), classPath.length(), "");
        classPath.append("/resources");
        while (classPath.indexOf("/") != -1) {
            classPath.replace(classPath.indexOf("/"), classPath.indexOf("/") + 1, "\\");
        }
        return classPath.toString();
    }

    public static String getIconPath() {
        return getResourcesPath() + "\\icons\\icon.png";
    }
}
