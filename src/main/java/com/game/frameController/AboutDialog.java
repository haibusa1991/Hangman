package com.game.frameController;

import com.interfaces.Frame;
import com.logicController.LogicController;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.strings.AboutDialogStrings.*;

public class AboutDialog extends JDialog implements Frame {
    private JPanel aboutFrame;
    private JLabel label_info;
    private JLabel label_version;
    private JLabel label_github;
    private final Dimension WINDOW_SIZE = new Dimension(200, 200);

    public AboutDialog() {
        initializeFrame();
        attachListeners();
        initializeText();
    }

    private void initializeFrame() {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(aboutFrame);
        this.setSize(WINDOW_SIZE);
    }

    private void attachListeners() {
        label_github.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                label_github.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Utils.addUnderline(label_github);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label_github.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Utils.removeUnderline(label_github);
            }

            public void mouseClicked(MouseEvent e) {
                    LogicController.getInstance().aboutDialogLabelClick();
            }
        });
    }

    private void initializeText() {
        this.setName(ABOUT_DIALOG_TITLE);
        this.label_info.setText(ABOUT_DIALOG_AUTHOR);
        this.label_github.setText(ABOUT_DIALOG_GITHUB_LINK);
        this.label_version.setText(ABOUT_DIALOG_VERSION);
    }

    @Override
    public void showFrame() {
        this.setVisible(true);
    }

    @Override
    public void hideFrame() {
        this.dispose();
    }

    @Override
    public void gainsFocus() {

    }

    @Override
    public void losesFocus() {
        this.dispose();
    }
}
