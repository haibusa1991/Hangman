package com.commands;

import com.interfaces.HangmanFrame;
import com.interfaces.HideCommand;

public abstract class BaseHideCommand implements HideCommand {
    @Override
    public void execute(HangmanFrame frame) {
        frame.hideFrame();
    }

}
