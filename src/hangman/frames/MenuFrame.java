package hangman.frames;

import hangman.FrameController;
import hangman.Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    static MenuFrame instance = null;
    private JPanel menuFrame;
    private JButton button_about;
    private JButton button_exit;
    private JButton button_settings;
    private JButton button_continueGame;
    private JButton button_newGame;
    private final Dimension WINDOW_SIZE = new Dimension(250, 400);

    public static MenuFrame getInstance() {
        if (instance == null) {
            instance = new MenuFrame();
        }
        return instance;
    }

    private MenuFrame() {
        super("Menu");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuFrame);
        this.pack();
        this.setSize(WINDOW_SIZE);
        Helpers.setCentered(this);
        this.setVisible(true);
        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController.getInstance().terminateApp();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button_about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController.getInstance().showAboutDialog();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
