package com.gameController;

import com.enums.Difficulty;
import com.logicController.GraphicsManager;

public class MediumGame extends BaseGame {
    private static final Integer[] frames = new Integer[]{5, 6, 7, 8, 9, 10, 11, 13};

    public MediumGame(GraphicsManager graphicsManager) {
        super(graphicsManager, frames, Difficulty.MEDIUM);
    }
}
