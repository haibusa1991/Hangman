package com.interfaces;


import com.enums.FrameType;

public interface HideFrameCommand {

    void execute(HangmanFrame frame);

    FrameType getFrameType();
}
