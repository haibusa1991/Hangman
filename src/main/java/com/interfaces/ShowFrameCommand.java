package com.interfaces;

import com.enums.FrameType;

import java.io.IOException;

public interface ShowFrameCommand {

    HangmanFrame execute();

    FrameType getFrameType();

    HangmanFrame getFrame();
}
