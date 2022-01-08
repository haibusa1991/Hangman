package com.dialogs;

import javax.swing.*;

public class InfoDialog {

    public InfoDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "FYI", JOptionPane.INFORMATION_MESSAGE); //todo fix hardcoded string
    }
}
