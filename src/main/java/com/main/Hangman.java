package com.main;

import com.game.frameController.ErrorDialog;
import com.game.frameController.FrameController;
import com.logicController.StateRepository;

public class Hangman {
    public static void main(String[] args) {

        StateRepository.getInstance();

        try {
            FrameController.getInstance().showMenuFrame();
        } catch (Exception e) {
            new ErrorDialog("Unable to initialize user interface.");
        }



    }
}

