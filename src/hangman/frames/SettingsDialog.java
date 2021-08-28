package hangman.frames;

import hangman.Helpers;
import hangman.LogicController.LogicController;

import javax.swing.*;
import javax.swing.plaf.ToolTipUI;
import java.awt.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton radioButton_easyDifficulty;
    private JRadioButton radioButton_mediumDifficulty;
    private JRadioButton radioButton_hardDifficulty;

    public boolean getRadioButton_easyDifficulty() {
        return radioButton_easyDifficulty.isSelected();
    }

    public void setRadioButton_easyDifficulty(boolean state) {
        this.radioButton_easyDifficulty.setSelected(state);
    }

    public boolean getRadioButton_mediumDifficulty() {
        return radioButton_mediumDifficulty.isSelected();
    }

    public void setRadioButton_mediumDifficulty(boolean state) {
        this.radioButton_mediumDifficulty.setSelected(state);
    }

    public boolean getRadioButton_hardDifficulty() {
        return radioButton_hardDifficulty.isSelected();
    }

    public void setRadioButton_hardDifficulty(boolean state) {
        this.radioButton_hardDifficulty.setSelected(state);
    }

    public boolean getCheckBox_onlineMode() {
        return checkBox_onlineMode.isSelected();
    }

    public void setCheckBox_onlineMode(boolean state) {
        this.checkBox_onlineMode.setSelected(state);
    }

    public boolean getCheckBox_saveOnExit() {
        return checkBox_saveOnExit.isSelected();
    }

    public void setCheckBox_saveOnExit(boolean state) {
        this.checkBox_saveOnExit.setSelected(state);
    }

    private JCheckBox checkBox_onlineMode;
    private JCheckBox checkBox_saveOnExit;
    private static SettingsDialog instance = null;

    public static SettingsDialog getInstance() {
        if (instance == null) {
            instance = new SettingsDialog();
            return instance;
        }
        return instance;
    }

    private SettingsDialog() {
        ToolTipManager.sharedInstance().setInitialDelay(50);
        setContentPane(contentPane);
        setModal(true);
        this.setResizable(false);
        this.pack();
        Helpers.setCentered(this);
        ButtonGroup difficulties = new ButtonGroup();
        difficulties.add(radioButton_easyDifficulty);
        difficulties.add(radioButton_mediumDifficulty);
        difficulties.add(radioButton_hardDifficulty);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LogicController.getInstance().saveSettings();
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    public void showSettingsDialog() {
        Helpers.setCentered(getInstance());
        getInstance().setVisible(true);
    }

}
