package game.frameController;

import logicController.Difficulty;
import logicController.LogicController;
import logicController.Settings;
import utils.Utils;

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
        setContentPane(this.contentPane);
        setModalityType(ModalityType.APPLICATION_MODAL);
        this.setResizable(false);
        this.pack();
        Utils.setCentered(this);

        ButtonGroup difficulties = new ButtonGroup();
        difficulties.add(this.radioButton_easyDifficulty);
        difficulties.add(this.radioButton_mediumDifficulty);
        difficulties.add(this.radioButton_hardDifficulty);

        loadSettings();

        this.buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

        this.buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void loadSettings() {
        LogicController lc = LogicController.getInstance();
        this.settings = lc.readSettingsFromDisk();
        updateDialogValues();
    }

    private void onCancel() {
        dispose();
    }

    protected void showDialog() {
        if (!this.isVisible()) {
            Utils.setCentered(this);
            this.setVisible(true);
        }
    }

    private void updateDialogValues() {
        zeroOutFields();

        if (this.settings.difficulty == Difficulty.EASY) {
            this.radioButton_easyDifficulty.setSelected(true);
        } else if (this.settings.difficulty == Difficulty.MEDIUM) {
            this.radioButton_mediumDifficulty.setSelected(true);
        } else {
            this.radioButton_hardDifficulty.setSelected(true);
        }

        this.checkBox_onlineMode.setSelected(settings.isOnline);
        this.checkBox_saveOnExit.setSelected(settings.doesSaveOnExit);
    }

    private void zeroOutFields() {
        this.radioButton_easyDifficulty.setSelected(false);
        this.radioButton_mediumDifficulty.setSelected(false);
        this.radioButton_hardDifficulty.setSelected(false);
        this.checkBox_onlineMode.setSelected(false);
        this.checkBox_saveOnExit.setSelected(false);
    }

    private void saveSettings() {

        if (this.radioButton_easyDifficulty.isSelected()) {
            this.settings.difficulty = Difficulty.EASY;
        } else if (this.radioButton_mediumDifficulty.isSelected()) {
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
