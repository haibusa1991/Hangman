package hangman.frameController;

import hangman.util.Util;
import hangman.logicController.LogicController;

import javax.swing.*;
import java.awt.event.*;

public class ErrorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label_message;

    public ErrorDialog(String message) {
        setContentPane(contentPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(this.contentPane);
        setSize(300, 120);
        setResizable(false);
        setAlwaysOnTop(true);
        Util.setCentered(this);
        label_message.setText(message);


        this.buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAnyButtonClick();
            }
        });

        // call onAnyButtonClick() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onAnyButtonClick();
            }
        });

        // call onAnyButtonClick() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAnyButtonClick();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAnyButtonClick();
            }
        });

        this.setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
    }

    private void onAnyButtonClick() {
//        LogicController lc = LogicController.getInstance();
//        lc.terminateApp();
        this.dispose();
    }

}
