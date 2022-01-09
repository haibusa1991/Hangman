package com.frameCommands;

import com.interfaces.HangmanFrame;
import com.interfaces.HideFrameCommand;

public abstract class BaseHideFrameCommand implements HideFrameCommand {
    @Override
    public void execute(HangmanFrame frame) {
        frame.hideFrame();
    }

}
