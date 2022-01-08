package com.commands;

import com.enums.FrameType;

public class HideMenuFrameCommand extends BaseHideCommand{

    @Override
    public FrameType getFrameType() {
        return FrameType.MENU_FRAME;
    }
}
