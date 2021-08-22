package hangman.frames;

import hangman.FrameController;
import hangman.Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HangmanFrame extends JFrame {
    private static HangmanFrame instance = null;
    private JPanel defaultPanel;
    private JButton button_close;
    private JLabel label_mode;
    private JButton button_getRandomWord;
    private JLabel label_randomWord;

    public String getLabel_modeText() {
        return label_mode.getText();
    }

    public void setLabel_modeText(String text) {
        this.label_mode.setText(text);
    }

    public String getLabel_randomWord() {
        return label_randomWord.getText();
    }

    public void setLabel_randomWord(String text) {
        this.label_randomWord.setText(text);
    }


    Dimension WINDOW_SIZE = new Dimension(640, 480);

    private HangmanFrame() {
        super("Hangman");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(defaultPanel);
        this.pack();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Helpers.getIconPath()));
        this.setSize(WINDOW_SIZE);
        Helpers.setCentered(this);
        this.setVisible(true);

        button_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController.getInstance().terminateApp();
                } catch (UnsupportedLookAndFeelException unsupportedLookAndFeelException) {
                    unsupportedLookAndFeelException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (InstantiationException instantiationException) {
                    instantiationException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        });

        button_getRandomWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FrameController.getRandomWord();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
    }

    public static HangmanFrame getInstance() {
        if (instance == null) {
            instance = new HangmanFrame();
        }
        return instance;
    }



}
