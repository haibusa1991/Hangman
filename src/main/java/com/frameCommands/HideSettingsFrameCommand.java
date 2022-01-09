package com.frameCommands;

import com.enums.FrameType;

public class HideSettingsFrameCommand extends BaseHideFrameCommand {

    @Override
    public FrameType getFrameType() {
        return FrameType.SETTINGS_FRAME;
    }
}
