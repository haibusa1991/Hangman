package com.frameCommands;

import com.frames.GameFrame;

public class ShowGameFrameCommand extends BaseShowFrameCommand{
    public ShowGameFrameCommand() {
        super(new GameFrame());
    }
}
