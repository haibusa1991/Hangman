package com.commands;

import com.frames.AboutFrame;

public class ShowAboutFrameCommand extends BaseShowCommand {

    public ShowAboutFrameCommand() {
        super(new AboutFrame());
    }
}
