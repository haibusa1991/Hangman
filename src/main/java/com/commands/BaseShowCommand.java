package com.commands;

import com.enums.FrameType;
import com.interfaces.HangmanFrame;
import com.interfaces.ShowCommand;

public abstract class BaseShowCommand implements ShowCommand {
    private final HangmanFrame frame;

    public BaseShowCommand(HangmanFrame frame) {
        this.frame = frame;
    }

    @Override
    public HangmanFrame execute() {
        frame.showFrame();
        return frame;
    }

    @Override
    public FrameType getFrameType() {
        return frame.getFrameType();
    }

    public HangmanFrame getFrame() {
        return frame;
    }
}
