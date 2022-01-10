package com.gameController;

import com.frames.GameFrame;
import com.logicController.GraphicsManager;

/**
 * responsible for displaying the correct image on screen, revealing the correct letters from the word,
 * chooses which letters to disable, keeps track of which letters are disabled.
 * Some more things probably, but it's 0:45 so...
 */
public class GameController {
    GraphicsManager graphicsManager;
    GameFrame gameFrame;

    public GameController(GameFrame gameFrame, GraphicsManager graphicsManager) {
        this.gameFrame = gameFrame;
        this.graphicsManager = graphicsManager;
    }

    public void letterClick(String letter){
        System.out.printf("Passed letter \"%s\"%s",letter,System.lineSeparator());
    }

}
