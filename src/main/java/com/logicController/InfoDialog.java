package com.logicController;

import javax.swing.*;

public class InfoDialog {

    public InfoDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "FIY", JOptionPane.INFORMATION_MESSAGE); //todo fix hardcoded string
    }
}
