package com.frameCommands;

import com.frames.MenuFrame;

public class ShowMenuFrameCommand extends BaseShowFrameCommand {

    public ShowMenuFrameCommand() {
        super(new MenuFrame());
    }
}
