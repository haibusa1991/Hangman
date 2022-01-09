package com.dialogs;

import com.interfaces.HangmanDialog;

import javax.swing.*;

import static com.strings.DialogMessages.*;

public class SaveGameConfirmationDialog implements HangmanDialog {
    private Integer result;

    @Override
    public void showDialog(String message) {
        result = JOptionPane.showConfirmDialog(null,
                SAVE_GAME_DIALOG_QUESTION,
                SAVE_GAME_DIALOG_TITLE,
                JOptionPane.YES_NO_CANCEL_OPTION);
    }

    @Override
    public Integer getResult() {
        return result;
    }
}
