package com.logicController;

import com.interfaces.HangmanFrame;
import com.interfaces.HideCommand;
import com.interfaces.ShowCommand;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FrameController {
    private final List<HangmanFrame> visibleFrames;

    public FrameController() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        visibleFrames = new ArrayList<>();
    }

    public void showFrame(ShowCommand command) {
        for (HangmanFrame frame : visibleFrames) {
            if (frame.getFrameType() == command.getFrameType()) {
                frame.showFrame();
                return;
            }
        }

        HangmanFrame frame = command.execute();
        visibleFrames.add(frame);
    }

    public void hideFrame(HideCommand command) {
        for (HangmanFrame frame : visibleFrames) {
            if (frame.getFrameType() == command.getFrameType()) {
                visibleFrames.remove(frame);
                command.execute(frame);
                return;
            }
        }
    }

}
