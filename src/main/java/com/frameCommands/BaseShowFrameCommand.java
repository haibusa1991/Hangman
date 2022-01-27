package com.frameCommands;

import com.enums.FrameType;
import com.interfaces.HangmanFrame;
import com.interfaces.ShowFrameCommand;
import com.logicController.FileHandler;
import com.logicController.LogicController;
import com.utils.Utils;

import javax.swing.*;
import java.io.IOException;

public abstract class BaseShowFrameCommand implements ShowFrameCommand {
    private final HangmanFrame frame;

    public BaseShowFrameCommand(HangmanFrame frame) {
        this.frame = frame;
    }

    @Override
    public HangmanFrame execute() {
        frame.showFrame();
        ((JFrame) frame).setIconImage(LogicController.getInstance().getWindowIcon());
        return frame;
    }

    @Override
    public FrameType getFrameType() {
        return frame.getFrameType();
    }

    @Override
    public HangmanFrame getFrame() {
        return this.frame;
    }
}
