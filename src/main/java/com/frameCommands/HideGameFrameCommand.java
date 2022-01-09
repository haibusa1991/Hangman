package com.frameCommands;

import com.enums.FrameType;

public class HideGameFrameCommand extends BaseHideFrameCommand{

    @Override
    public FrameType getFrameType() {
        return FrameType.GAME_FRAME;
    }
}
