package com.interfaces;

import com.enums.FrameType;

public interface ShowCommand {

    HangmanFrame execute();

    FrameType getFrameType();
}
