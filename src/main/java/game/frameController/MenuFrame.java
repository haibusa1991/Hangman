package game.frameController;

import io.FileHandler;
import logicController.LogicController;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame {
    static MenuFrame instance = null;

    private JPanel menuFrame;

    private JButton button_about;
    private JButton button_exit;
    private JButton button_settings;
    private JButton button_continueGame;
    private JButton button_newGame;

    private JLabel label_poweredBy;

    private final Dimension WINDOW_SIZE = new Dimension(250, 400);

    protected static MenuFrame getInstance() {
        if (instance == null) {
            instance = new MenuFrame();
        }
        attachWindowListener();
        return instance;
    }

    private MenuFrame() {
        super("Hangman");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuFrame);
//        this.setIconImage(new FileHandler().getApplicationIcon());//todo fix path
        this.pack();
        this.setSize(WINDOW_SIZE);
        Utils.setCentered(this);

        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LogicController lc = LogicController.getInstance();
                    lc.terminateApp();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        button_about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController fc = FrameController.getInstance();
                    fc.showAboutDialog();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        label_poweredBy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogicController.getInstance().openFunlandSite();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label_poweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Utils.setUnderlined(label_poweredBy);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label_poweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Utils.setPlain(label_poweredBy);
            }
        });

        button_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController fc = FrameController.getInstance();
                    fc.showSettingsDialog();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        this.button_newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {//todo refactor this

                    FrameController fc = FrameController.getInstance();
                    fc.showGameFrame();
                } catch (Exception ignored) {
                }
            }
        });
    }

    private static void attachWindowListener() {
        instance.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                try {//todo refactor

                    FrameController fc = FrameController.getInstance();
                    fc.closeAboutDialog();
                } catch (Exception ignored) {
                }
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                //clean up later perhaps
            }
        });
    }

    protected void showFrame() {
        if (!this.isVisible()) {
            this.setVisible(true);
        }
    }
}
