package hangman;

import hangman.LogicController.LogicController;

import javax.swing.*;
import java.io.IOException;

public class Hangman {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        FrameController.getInstance();
        LogicController.getInstance();
    }
}
