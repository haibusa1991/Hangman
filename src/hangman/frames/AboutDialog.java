package hangman.frames;

import hangman.FrameController;
import hangman.Helpers;
import hangman.LogicController.LogicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AboutDialog extends JDialog {
    private JPanel aboutFrame;
    private JLabel label_info;
    private JLabel label_version;
    private JLabel label_github;
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
        label_github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label_github.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Helpers.setUnderlined(label_github);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label_github.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Helpers.setPlain(label_github);
            }

            public void mouseClicked(MouseEvent e) {
                try {
                    LogicController.getInstance().openGithub();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void showAboutDialog() {
        if (!this.isVisible()) {
            Helpers.setCentered(this);
            this.setVisible(true);
        }
    }

}
