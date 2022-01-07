package com.game.frameController;

import com.interfaces.Frame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class FrameController {
    private List<Frame> visibleFrames;
//todo remove all static stuff on frames and make them inherit Frame interface

    public FrameController() throws
            UnsupportedLookAndFeelException,
            ClassNotFoundException,
            InstantiationException,
            IllegalAccessException {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        visibleFrames = new ArrayList<>();
    }

    public void showFrame(Frame frame) {
        frame.showFrame();
    }

    public void hideFrame(Frame frame) {
        frame.hideFrame();
    }

    public void isolate(Frame frame) {
//      executes hideFrame on all frames in visibleFrames then removes them from the collection
    }

//    public void showMenuFrame() {
//        MenuFrame.getInstance().showFrame();
//    }

//    public void showAboutDialog() {
//        new AboutDialog();
//    }

//    private void isolateGameFrame() {
//        closeAboutDialog();
//        MenuFrame.getInstance().dispose();
//        GameFrame.getInstance().showFrame();
//    }


//    public void showGameFrame() {
//        isolateGameFrame();
//        GameController gameController = new GameController(GameFrame)
//
//        LogicController lc = LogicController.getInstance();
//        BufferedImage bi = lc.readMockImage();
//
//        GameFrame gf = GameFrame.getInstance();
//        gf.setMockImage(bi);
//        gf.showFrame();
//    }


}
