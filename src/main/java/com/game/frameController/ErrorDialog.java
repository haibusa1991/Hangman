package com.game.frameController;

import javax.swing.*;

public class ErrorDialog{

    public ErrorDialog(String message) {
        JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);
    }
}


