package hangman;

import hangman.frameController.FrameController;
import hangman.logicController.LogicController;

import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {

        FrameController fc = FrameController.getInstance();
        fc.showMenuFrame();

        LogicController lc = LogicController.getInstance();
    }
}

