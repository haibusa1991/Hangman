package com.interfaces;


import com.enums.FrameType;

public interface HideCommand {

    void execute(HangmanFrame frame);

    FrameType getFrameType();
}
