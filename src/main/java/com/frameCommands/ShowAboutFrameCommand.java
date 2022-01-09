package com.frameCommands;

import com.frames.AboutFrame;

public class ShowAboutFrameCommand extends BaseShowFrameCommand {

    public ShowAboutFrameCommand() {
        super(new AboutFrame());
    }
}
