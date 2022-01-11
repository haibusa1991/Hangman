package com.gameController;

import com.enums.Difficulty;
import com.logicController.GraphicsManager;

public class HardGame extends BaseGame {
    private static final Integer[] frames = new Integer[]{5, 7, 10, 13};

    public HardGame(GraphicsManager graphicsManager) {
        super(graphicsManager, frames, Difficulty.MEDIUM);
    }
}
