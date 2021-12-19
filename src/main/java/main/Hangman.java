package main;

import game.frameController.ErrorDialog;
import game.frameController.FrameController;
import logicController.StateRepository;

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

