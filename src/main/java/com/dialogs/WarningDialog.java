package com.dialogs;

import com.interfaces.HangmanDialog;

import javax.swing.*;

import static com.strings.WarningMessages.*;

public class WarningDialog implements HangmanDialog {

    @Override
    public void showDialog(String message) {
        JOptionPane.showMessageDialog(null, message, WARNING_DIALOG_TITLE, JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public Integer getResult() {
        return null;
    }
}
