package hangman.frames;

import hangman.FrameController;
import hangman.Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.EventListener;

public class AboutDialog extends JDialog {
    private JPanel aboutFrame;
    private JLabel label_info;
    private static AboutDialog instance = null;
    private final Dimension WINDOW_SIZE = new Dimension(200, 200);

    public static AboutDialog getInstance() {
        if (instance == null) {
            instance = new AboutDialog();
        }
        return instance;
    }

    private AboutDialog() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(aboutFrame);
        this.setSize(WINDOW_SIZE);
        this.setAlwaysOnTop(true);//?
    }

    public void showDialog() {
        if (!this.isVisible()) {
            Helpers.setCentered(this);
            this.setVisible(true);
        }else {
            this.toFront();
            this.repaint();
        }
    }

    public boolean isCurrentlyVisible(){
        return getInstance().aboutFrame.isVisible();
    }

}
