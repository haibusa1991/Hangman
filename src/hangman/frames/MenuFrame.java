package hangman.frames;

import hangman.FrameController;
import hangman.Helpers;

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

    public static MenuFrame getInstance() {
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
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Helpers.getWindowIconPath()));
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
        label_poweredBy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrameController.openFunlandSite();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label_poweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                Helpers.setUnderlined(label_poweredBy);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label_poweredBy.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Helpers.setPlain(label_poweredBy);
            }
        });
        button_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController.getInstance().showSettingsDialog();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }

    private static void attachWindowListener() {
        instance.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                FrameController.closeOtherFrames();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                //clean up later perhaps
            }
        });
    }
}
