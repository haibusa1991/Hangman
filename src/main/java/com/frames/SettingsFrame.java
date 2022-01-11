package com.frames;

import com.enums.FrameType;
import com.interfaces.HangmanFrame;
import com.enums.Difficulty;
import com.logicController.LogicController;
import com.logicController.Settings;
import com.utils.Utils;

import static com.strings.SettingsFrameStrings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

public class SettingsFrame extends JDialog implements HangmanFrame {
    private JPanel settingsFrame;

    private JButton buttonSave;
    private JButton buttonCancel;

    private ButtonGroup difficulties;
    private JRadioButton radioButtonEasyDifficulty;
    private JRadioButton radioButtonMediumDifficulty;
    private JRadioButton radioButtonHardDifficulty;

    private JCheckBox checkBoxOnlineMode;
    private JCheckBox checkBoxSaveOnExit;

    private JLabel labelDifficulty;

    private final Dimension WINDOW_SIZE = new Dimension(200, 250);


    public SettingsFrame() {
        attachListeners();
        initializeFrame();
        initializeText();

    }

    private void initializeFrame() {
        ToolTipManager.sharedInstance().setInitialDelay(50);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setContentPane(this.settingsFrame);
        this.setModalityType(ModalityType.APPLICATION_MODAL);

        this.setSize(WINDOW_SIZE);
        this.setResizable(false);
        Utils.centerFrame(this);

        difficulties = new ButtonGroup();
        difficulties.add(this.radioButtonEasyDifficulty);
        difficulties.add(this.radioButtonMediumDifficulty);
        difficulties.add(this.radioButtonHardDifficulty);
    }

    private void initializeText() {
        this.setTitle(SETTINGS_FRAME_TITLE);

        this.buttonSave.setText(SETTINGS_FRAME_SAVE_BUTTON);
        this.buttonCancel.setText(SETTINGS_FRAME_CANCEL_BUTTON);

        this.radioButtonEasyDifficulty.setText(SETTINGS_FRAME_DIFFICULTY_EASY);
        this.radioButtonMediumDifficulty.setText(SETTINGS_FRAME_DIFFICULTY_MEDIUM);
        this.radioButtonHardDifficulty.setText(SETTINGS_FRAME_DIFFICULTY_HARD);

        this.checkBoxOnlineMode.setText(SETTINGS_FRAME_ONLINE_MODE);
        this.checkBoxOnlineMode.setToolTipText(SETTINGS_FRAME_ONLINE_MODE_TOOLTIP);

        this.checkBoxSaveOnExit.setText(SETTINGS_FRAME_SAVE_ON_EXIT);
        this.checkBoxSaveOnExit.setToolTipText(SETTINGS_FRAME_SAVE_ON_EXIT_TOOLTIP);

        this.labelDifficulty.setText(SETTINGS_FRAME_DIFFICULTY);
    }

    private void attachListeners() {
        LogicController lc = LogicController.getInstance();

        this.buttonSave.addActionListener(e -> lc.settingsFrameButtonClickSave(getSettingsFrameState()));

        this.buttonCancel.addActionListener(e -> lc.settingsFrameIsClosed());

        // discard settings on cross click
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                lc.settingsFrameIsClosed();
            }
        });

        // discard settings on escape key
        this.settingsFrame.registerKeyboardAction(e -> lc.settingsFrameIsClosed(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_FOCUSED);
    }


    public void setSettingsFrameState(Settings settings) {
        this.checkBoxOnlineMode.setSelected(settings.isOnline);
        this.checkBoxSaveOnExit.setSelected(settings.doesSaveOnExit);

        switch (settings.difficulty) {

            case EASY -> {
                this.radioButtonEasyDifficulty.setSelected(true);
            }
            case MEDIUM -> {
                this.radioButtonMediumDifficulty.setSelected(true);
            }
            case HARD -> {
                this.radioButtonHardDifficulty.setSelected(true);
            }
        }
    }

    public Settings getSettingsFrameState() {
        Settings settings = new Settings();

        settings.isOnline = this.checkBoxOnlineMode.isSelected();
        settings.doesSaveOnExit = this.checkBoxSaveOnExit.isSelected();

        Enumeration<AbstractButton> enumeration = difficulties.getElements();
        AtomicInteger difficulty = new AtomicInteger(0);
        while (enumeration.hasMoreElements()) {
            AbstractButton abstractButton = enumeration.nextElement();
            if (abstractButton.isSelected()) {
                break;
            }
            difficulty.incrementAndGet();
        }

        settings.difficulty = Arrays.stream(Difficulty.values())
                .filter(e -> e.ordinal() == difficulty.get())
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
    public FrameType getFrameType() {
        return FrameType.SETTINGS_FRAME;
    }

}
