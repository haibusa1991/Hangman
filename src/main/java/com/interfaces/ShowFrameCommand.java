package com.interfaces;

import com.enums.FrameType;

public interface ShowFrameCommand {

    HangmanFrame execute();

    FrameType getFrameType();

    HangmanFrame getFrame();
}
