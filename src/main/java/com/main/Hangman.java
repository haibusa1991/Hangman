package com.main;

import com.dialogs.ErrorDialog;
import com.logicController.LogicController;

public class Hangman {

    public static void main(String[] args) {

        try {
            LogicController.getInstance();
        } catch (Exception e){
            new ErrorDialog(e.getMessage());
            LogicController.terminateApp();
        }


    }
}

