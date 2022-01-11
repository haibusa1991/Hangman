package com.gameController;

import com.enums.Difficulty;
import com.logicController.GraphicsManager;

public class EasyGame extends BaseGame {
    private static final Integer[] frames = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13};

    public EasyGame(GraphicsManager graphicsManager) {
        super(graphicsManager, frames, Difficulty.EASY);
    }
}
