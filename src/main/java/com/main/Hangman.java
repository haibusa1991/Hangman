package com.main;

import com.logicController.ErrorDialog;
import com.game.frameController.FrameController;
import com.logicController.LogicController;

import javax.swing.*;

public class Hangman {

    public static void main(String[] args) {

        try {
            LogicController.getInstance().showMenu();
        } catch (Exception e){
            new ErrorDialog(e.getMessage());
            LogicController.terminateApp();
        }


    }
}

