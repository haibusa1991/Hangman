package hangman;

import hangman.frameController.FrameController;
import hangman.logicController.LogicController;

public class Hangman {
    public static void main(String[] args) {

        FrameController fc = FrameController.getInstance();
        fc.showMenuFrame();

        LogicController lc = LogicController.getInstance();
        lc.createEmptySettings();
    }
}

