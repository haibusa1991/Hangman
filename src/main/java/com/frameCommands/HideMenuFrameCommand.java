package com.frameCommands;

import com.enums.FrameType;

public class HideMenuFrameCommand extends BaseHideFrameCommand {

    @Override
    public FrameType getFrameType() {
        return FrameType.MENU_FRAME;
    }
}
