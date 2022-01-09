package com.frameCommands;

import com.enums.FrameType;

public class HideAboutFrameCommand extends BaseHideFrameCommand {

    @Override
    public FrameType getFrameType() {
        return FrameType.ABOUT_FRAME;
    }
}
