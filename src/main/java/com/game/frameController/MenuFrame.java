package com.game.frameController;

import com.interfaces.Frame;
import com.logicController.LogicController;
import com.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.strings.MenuFrameStrings.*;

public class MenuFrame extends JFrame implements Frame {

    private JPanel menuFrame;

    private JButton button_about;
    private JButton button_exit;
    private JButton button_settings;
    private JButton button_continueGame;
    private JButton button_newGame;

    private JLabel label_poweredBy;

    private final Dimension WINDOW_SIZE = new Dimension(250, 400);

    public MenuFrame() {
        initializeFrame();
        attachListeners();
//        attachWindowListener();
        initializeText();
    }

    private void initializeFrame() {

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuFrame);
//        this.setIconImage(new FileHandler().getApplicationIcon());//todo fix path and implementation
        this.pack();
        this.setSize(WINDOW_SIZE);
        Utils.centerFrame(this);
    }

    private void attachWindowListener() {
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                gainsFocus();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                losesFocus();
            }
        });
    }

    private void attachListeners() {

        this.button_newGame.addActionListener(e -> LogicController.getInstance().menuButtonClickNewGame());

        this.button_continueGame.addActionListener(e -> LogicController.getInstance().menuButtonClickContinueGame());

        this.button_settings.addActionListener(e -> LogicController.getInstance().menuButtonClickSettings());

        this.button_about.addActionListener(e -> LogicController.getInstance().menuButtonClickAbout());

        button_exit.addActionListener(e -> LogicController.getInstance().menuButtonClickExit());

        label_poweredBy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogicController.getInstance().menuLabelClickFunland();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label_poweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Utils.addUnderline(label_poweredBy);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label_poweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Utils.removeUnderline(label_poweredBy);
            }
        });

    }

    private void initializeText() {
        this.setTitle(MENU_TITLE);

        this.button_newGame.setText(MENU_NEW_GAME);
        this.button_continueGame.setText(MENU_CONTINUE_GAME);
        this.button_settings.setText(MENU_SETTINGS);
        this.button_about.setText(MENU_ABOUT);
        this.button_exit.setText(MENU_EXIT);

        this.label_poweredBy.setText(MENU_POWERED_BY_FUNLAND);
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
        //todo implement what happens on window focus gain - discards settings if changed; closes about dialog
        //LogicController.getInstance().isolateFrame(this);
    }

    @Override
    public void losesFocus() {
        //todo implement what happens on window focus loss
    }
}
