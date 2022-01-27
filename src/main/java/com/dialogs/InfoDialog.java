package com.dialogs;

import javax.swing.*;

import static com.strings.DialogMessages.*;

public class InfoDialog {

    public InfoDialog(String message) {
        JOptionPane.showMessageDialog(null, message, INFORMATION_DIALOG_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}
