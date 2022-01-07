package com.game.frameController;

import com.interfaces.Frame;
import com.logicController.Difficulty;
import com.logicController.LogicController;
import com.logicController.Settings;
import com.utils.Utils;

import static com.strings.SettingsStrings.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

public class SettingsDialog extends JDialog implements Frame {
    private JPanel contentPane;

    private JButton buttonSave;
    private JButton buttonCancel;

    private ButtonGroup difficulties;
    private JRadioButton radioButton_easyDifficulty;
    private JRadioButton radioButton_mediumDifficulty;
    private JRadioButton radioButton_hardDifficulty;

    private JCheckBox checkBox_onlineMode;
    private JCheckBox checkBox_saveOnExit;

    private JLabel labelDifficulty;


    public SettingsDialog() {
        initializeFrame();
        attachListeners();
        initializeText();
    }

    private void initializeText() {
        this.setTitle(SETTINGS_TITLE);

        this.buttonSave.setText(SETTINGS_SAVE_BUTTON);
        this.buttonCancel.setText(SETTINGS_CANCEL_BUTTON);

        this.radioButton_easyDifficulty.setText(SETTINGS_DIFFICULTY_EASY);
        this.radioButton_mediumDifficulty.setText(SETTINGS_DIFFICULTY_MEDIUM);
        this.radioButton_hardDifficulty.setText(SETTINGS_DIFFICULTY_HARD);

        this.checkBox_onlineMode.setText(SETTINGS_ONLINE_MODE);
        this.checkBox_saveOnExit.setText(SETTINGS_SAVE_ON_EXIT);

        this.labelDifficulty.setText(SETTINGS_DIFFICULTY);
    }


    private void attachListeners() {
        this.buttonSave.addActionListener(e -> LogicController.getInstance().settingsButtonClickSave(this));

        this.buttonCancel.addActionListener(e -> hideFrame());

        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                hideFrame();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(e -> hideFrame(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }


    private void initializeFrame() {
        ToolTipManager.sharedInstance().setInitialDelay(50);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(this.contentPane);
        setModalityType(ModalityType.APPLICATION_MODAL);
        this.setResizable(false);
        this.pack();
        Utils.centerFrame(this);

        difficulties = new ButtonGroup();
        difficulties.add(this.radioButton_easyDifficulty);
        difficulties.add(this.radioButton_mediumDifficulty);
        difficulties.add(this.radioButton_hardDifficulty);
    }


    public void setState(Settings settings) {
        this.checkBox_onlineMode.setSelected(settings.isOnline);
        this.checkBox_saveOnExit.setSelected(settings.doesSaveOnExit);

        switch (settings.difficulty) {

            case EASY -> {
                this.radioButton_easyDifficulty.setSelected(true);
            }
            case MEDIUM -> {
                this.radioButton_mediumDifficulty.setSelected(true);
            }
            case HARD -> {
                this.radioButton_hardDifficulty.setSelected(true);
            }
        }
    }

    public Settings getState() {
        Settings settings = new Settings();

        settings.isOnline = this.checkBox_onlineMode.isSelected();
        settings.doesSaveOnExit = this.checkBox_saveOnExit.isSelected();

        Enumeration<AbstractButton> enumeration = difficulties.getElements();
        AtomicInteger difficulty= new AtomicInteger(0);
        while (enumeration.hasMoreElements()){
            AbstractButton abstractButton = enumeration.nextElement();
            if(abstractButton.isSelected()){
                break;
            }
            difficulty.incrementAndGet();
        }

        settings.difficulty= Arrays.stream(Difficulty.values())
                .filter(e->e.ordinal()==difficulty.get())
                .findFirst()
                .get();

        return settings;
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

    }

    @Override
    public void losesFocus() {

    }
}
