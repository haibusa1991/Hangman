package hangman;

import hangman.frames.HangmanFrame;
import hangman.frames.MenuFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        HangmanFrame frame = HangmanFrame.getInstance();
//        FrameController controller = FrameController.getInstance();
//        WordGenerator wordGenerator = WordGenerator.getInstance();
//        wordGenerator.setOnlineMode();
        FrameController.getInstance();
    }
}
