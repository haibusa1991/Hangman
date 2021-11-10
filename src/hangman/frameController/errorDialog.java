package hangman.frameController;

import hangman.Helpers;

import javax.swing.*;
import java.awt.event.*;

public class errorDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label_message;

    public errorDialog(String message) {
        setContentPane(contentPane);
        setModal(true);
        setSize(300, 120);
        setResizable(false);
        setAlwaysOnTop(true);
        Helpers.setCentered(this);
        getRootPane().setDefaultButton(buttonOK);
        label_message.setText(message);
        setVisible(true);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("FUY");
                onOK();
            }
        });
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("closing");
                onOK();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fu");
            }
        });
    }

    private void onOK() {
        this.dispose();
        System.exit(-1);
    }

}
