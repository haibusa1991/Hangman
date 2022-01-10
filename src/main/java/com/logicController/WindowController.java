package com.logicController;

import com.enums.FrameType;
import com.frames.GameFrame;
import com.interfaces.*;
import com.strings.ErrorMessages;

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

    public Integer showDialog(ShowDialogCommand command) {
        command.execute();
        return command.getDialogResult();
    }

    public GameFrame getGameFrame(){
        for (HangmanFrame frame : visibleFrames) {
            if(frame.getFrameType()== FrameType.GAME_FRAME){
                return (GameFrame) frame;
            }
        }
        throw new IllegalStateException(ErrorMessages.WINDOW_CONTROLLER_CANNOT_GET_GAME_FRAME);
    }

}
