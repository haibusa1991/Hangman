package com.frames;

import com.enums.FrameType;
import com.interfaces.HangmanFrame;
import com.logicController.LogicController;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.strings.MenuFrameStrings.*;

public class MenuFrame extends JFrame implements HangmanFrame {

    private JPanel menuFrame;

    private JButton buttonAbout;
    private JButton buttonExit;
    private JButton buttonSettings;
    private JButton buttonContinueGame;
    private JButton buttonNewGame;

    private JLabel labelPoweredBy;

    private final Dimension WINDOW_SIZE = new Dimension(250, 400);

    public MenuFrame() {
        initializeFrame();
        attachListeners();
        initializeText();
    }

    private void initializeFrame() {

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuFrame);
        this.pack();
        this.setSize(WINDOW_SIZE);
        Utils.centerFrame(this);
    }


    private void attachListeners() {
        LogicController lc = LogicController.getInstance();

        this.buttonNewGame.addActionListener(e -> lc.menuFrameButtonClickNewGame());
        this.buttonContinueGame.addActionListener(e -> lc.menuFrameButtonClickContinueGame());
        this.buttonSettings.addActionListener(e -> lc.menuFrameButtonClickSettings());
        this.buttonAbout.addActionListener(e -> lc.menuFrameButtonClickAbout());
        this.buttonExit.addActionListener(e -> lc.menuFrameButtonClickExit());

        this.labelPoweredBy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                lc.menuFrameLabelClickFunland();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                labelPoweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Utils.addUnderline(labelPoweredBy);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                labelPoweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Utils.removeUnderline(labelPoweredBy);
            }
        });

    }

    private void initializeText() {
        this.setTitle(MENU_TITLE);

        this.buttonNewGame.setText(MENU_NEW_GAME);
        this.buttonContinueGame.setText(MENU_CONTINUE_GAME);
        this.buttonSettings.setText(MENU_SETTINGS);
        this.buttonAbout.setText(MENU_ABOUT);
        this.buttonExit.setText(MENU_EXIT);

        this.labelPoweredBy.setText(MENU_POWERED_BY_FUNLAND);
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
    public FrameType getFrameType() {
        return FrameType.MENU_FRAME;
    }

}
