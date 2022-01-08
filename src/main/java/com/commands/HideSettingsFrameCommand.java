package com.commands;

import com.enums.FrameType;
import com.frames.SettingsFrame;
import com.interfaces.HangmanFrame;

public class HideSettingsFrameCommand extends BaseHideCommand {


    public void execute(HangmanFrame frame) {
        ((SettingsFrame) frame).dispose();
    }

    @Override
    public FrameType getFrameType() {
        return FrameType.SETTINGS_FRAME;
    }
}
