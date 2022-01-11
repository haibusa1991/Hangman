package com.frameCommands;

import com.frames.SettingsFrame;
import com.interfaces.HangmanFrame;
import com.logicController.LogicController;
import com.logicController.Settings;

public class ShowSettingFrameCommand extends BaseShowFrameCommand {

    public ShowSettingFrameCommand() {
        super(new SettingsFrame());
    }

    @Override
    public HangmanFrame execute() {
        SettingsFrame frame = (SettingsFrame) super.getFrame();
        Settings settings = LogicController.getInstance().getSettings();
        frame.setSettingsFrameState(settings);

        frame.showFrame();
        return super.getFrame();
    }
}
