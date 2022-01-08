package com.frames;

import com.enums.FrameType;
import com.interfaces.HangmanFrame;
import com.logicController.LogicController;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.strings.AboutFrameStrings.*;

public class AboutFrame extends JDialog implements HangmanFrame {
    private JPanel aboutFrame;
    private JLabel labelInfo;
    private JLabel labelVersion;
    private JLabel labelGithub;
    private final Dimension WINDOW_SIZE = new Dimension(200, 200);

    public AboutFrame() {
        initializeFrame();
        attachListeners();
        initializeText();
    }

    private void initializeFrame() {
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setContentPane(aboutFrame);
        this.setSize(WINDOW_SIZE);
    }

    private void attachListeners() {
        LogicController lc = LogicController.getInstance();

        this.labelGithub.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                labelGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Utils.addUnderline(labelGithub);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelGithub.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Utils.removeUnderline(labelGithub);
            }

            public void mouseClicked(MouseEvent e) {
                lc.aboutFrameLabelClick();
            }
        });

        //on cross click
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                lc.aboutFrameIsClosed();
            }
        });

        //on escape key press
        this.aboutFrame.registerKeyboardAction(e -> lc.aboutFrameIsClosed(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_FOCUSED);
    }

    private void initializeText() {
        this.setName(ABOUT_FRAME_TITLE);
        this.labelInfo.setText(ABOUT_FRAME_AUTHOR);
        this.labelGithub.setText(ABOUT_FRAME_GITHUB_LINK);
        this.labelVersion.setText(ABOUT_FRAME_VERSION);
    }

    @Override
    public void showFrame() {
        Utils.centerFrame(this);
        this.setVisible(true);
    }

    @Override
    public void hideFrame() {
        this.dispose();
    }

    @Override
    public FrameType getFrameType() {
        return FrameType.ABOUT_FRAME;
    }

}
