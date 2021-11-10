package hangman.frameController;

import hangman.Helpers;
import hangman.logicController.Difficulty;
import hangman.logicController.Settings;
import hangman.logicController.LogicController;

import javax.swing.*;
import java.awt.event.*;

public class SettingsDialog extends JDialog {
    private JPanel contentPane;

    private JButton buttonOK;
    private JButton buttonCancel;

    private JRadioButton radioButton_easyDifficulty;
    private JRadioButton radioButton_mediumDifficulty;
    private JRadioButton radioButton_hardDifficulty;

    private JCheckBox checkBox_onlineMode;
    private JCheckBox checkBox_saveOnExit;

    private static SettingsDialog instance = null;
    private Settings settings = null;

    protected static SettingsDialog getInstance() {
        if (instance == null) {
            instance = new SettingsDialog();
            return instance;
        }
        return instance;
    }

    private SettingsDialog() {
        ToolTipManager.sharedInstance().setInitialDelay(50);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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
                saveSettings();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onCancel() {
//        dispose();
        this.setVisible(false);
    }

    protected void showDialog() {
        if (!this.isVisible()) {
            Helpers.setCentered(this);
            updateDialogValues();
            this.setVisible(true);
        }
    }

    protected Settings getSettings() {
        return this.settings;
    }

    protected void setSettings(Settings settings) {
        if (this.settings != null) {
            return;
        }
        this.settings = settings;
        updateDialogValues();
    }


    private void updateDialogValues() {
        zeroOutFields();

        if (settings.difficulty == Difficulty.EASY) {
            this.radioButton_easyDifficulty.setSelected(true);
        } else if (settings.difficulty == Difficulty.MEDIUM) {
            this.radioButton_mediumDifficulty.setSelected(true);
        } else {
            this.radioButton_hardDifficulty.setSelected(true);
        }

        checkBox_onlineMode.setSelected(settings.isOnline);
        checkBox_saveOnExit.setSelected(settings.doesSaveOnExit);
    }

    private void zeroOutFields() {
        this.radioButton_easyDifficulty.setSelected(false);
        this.radioButton_mediumDifficulty.setSelected(false);
        this.radioButton_hardDifficulty.setSelected(false);
        checkBox_onlineMode.setSelected(false);
        checkBox_saveOnExit.setSelected(false);
    }

    private void saveSettings() {

        if (radioButton_easyDifficulty.isSelected()) {
            this.settings.difficulty = Difficulty.EASY;
        } else if (radioButton_mediumDifficulty.isSelected()) {
            this.settings.difficulty = Difficulty.MEDIUM;
        } else {
            this.settings.difficulty = Difficulty.HARD;
        }

        this.settings.isOnline = checkBox_onlineMode.isSelected();
        this.settings.doesSaveOnExit = checkBox_saveOnExit.isSelected();

        LogicController lc = LogicController.getInstance();
        lc.saveSettingsToDisk(this.settings);
        dispose();
    }

}
