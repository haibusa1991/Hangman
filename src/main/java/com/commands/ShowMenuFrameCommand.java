package com.commands;

import com.frames.MenuFrame;

public class ShowMenuFrameCommand extends BaseShowCommand{

    public ShowMenuFrameCommand() {
        super(new MenuFrame());
    }
}
