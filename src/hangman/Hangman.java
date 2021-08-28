package hangman;

import hangman.frames.HangmanFrame;
import hangman.frames.MenuFrame;

import javax.swing.*;
import java.io.IOException;

public class Hangman {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        FrameController.getInstance();
    }
}
