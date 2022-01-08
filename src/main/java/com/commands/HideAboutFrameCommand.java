package com.commands;

import com.enums.FrameType;

public class HideAboutFrameCommand extends BaseHideCommand {

    @Override
    public FrameType getFrameType() {
        return FrameType.ABOUT_FRAME;
    }
}
