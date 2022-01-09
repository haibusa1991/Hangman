package com.logicController;

import com.interfaces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class WindowController {
    private final List<HangmanFrame> visibleFrames;

    public WindowController() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        visibleFrames = new ArrayList<>();
    }

    public void showFrame(ShowFrameCommand command) {
        for (HangmanFrame frame : visibleFrames) {
            if (frame.getFrameType() == command.getFrameType()) {
                frame.showFrame();
                return;
            }
        }

        HangmanFrame frame = command.getFrame();
        visibleFrames.add(frame);
        command.execute();
    }

    public void hideFrame(HideFrameCommand command) {
        for (HangmanFrame frame : visibleFrames) {
            if (frame.getFrameType() == command.getFrameType()) {
                visibleFrames.remove(frame);
                command.execute(frame);
                return;
            }
        }
    }

    public void showDialog(ShowDialogCommand command){
        command.execute();
    }

}
