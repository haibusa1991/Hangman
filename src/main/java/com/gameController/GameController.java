package com.gameController;

import com.enums.Difficulty;
import com.frames.GameFrame;
import com.interfaces.HangmanGame;
import com.logicController.GraphicsManager;
import com.strings.GameFrameStrings;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Iterator;

/**
 * responsible for displaying the correct image on screen, revealing the correct letters from the word,
 * chooses which letters to disable, keeps track of which letters are disabled.
 * Some more things probably, but it's 0:45 so...
 */
public class GameController {
    GraphicsManager graphicsManager;
    GameFrame gameFrame;
    GameState gameState;

    String targetWord = null;
    Iterator<BufferedImage> hangingSteps = null;
    boolean canPlay = true;

    public GameController(GameFrame gameFrame, GraphicsManager graphicsManager) {
        this.gameFrame = gameFrame;
        this.graphicsManager = graphicsManager;
    }

    public void letterClick(Letter letter) {
        if (!canPlay) {
            return;
        }
        //todo refactor the if-else galore. Also selecting an already found letter should
        // punish the player


        GameState state = this.gameFrame.getGameState();
        if (!state.getWordState().contains("_")) {
            return;
        }

        String usedLetters = state.getUsedLetters();

        if (usedLetters.equals(GameFrameStrings.GAME_FRAME_LETTERS_USED)) {
            state.setUsedLetters(usedLetters + letter.getLetter());
        } else {
            state.setUsedLetters(state.getUsedLetters() + ", " + letter.getLetter());
        }

        if (this.targetWord.contains(letter.getLetter())) {
            int[] positions = getLetterIndices(letter);
            String updatedWord = replaceUnderscores(state.getWordState(), letter, positions);
            state.setWordState(updatedWord);
        } else {
            if (hangingSteps.hasNext()) {
                state.setCurrentStep(hangingSteps.next());
                if (!hangingSteps.hasNext()) {
                    canPlay = false;
                    state.setWordState(revealWord(this.targetWord));
                }
            } else {
                canPlay = false;
            }
        }
        gameFrame.setGameState(state);
    }

    private String revealWord(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(c).append(' ');
        }
        return sb.toString().trim();
    }


    public void startNewGame(Difficulty difficulty) {
        switch (difficulty) {
            case EASY -> initializeState(new EasyGame(this.graphicsManager));
            case MEDIUM -> initializeState(new MediumGame(this.graphicsManager));
            case HARD -> initializeState(new HardGame(this.graphicsManager));
        }
    }

    private void initializeState(HangmanGame hangmanGame) {
        this.targetWord = hangmanGame.getWord();
        this.hangingSteps = hangmanGame.iterator();
        this.canPlay = true;

        this.gameState = new GameState(
                maskWord(hangmanGame.getWord()),
                hangmanGame.getWordDescription(),
                hangmanGame.getFirstStep(),
                GameFrameStrings.GAME_FRAME_LETTERS_USED
        );

        this.gameFrame.setGameState(gameState);

    }

    private String maskWord(String word) {
        return "_ ".repeat(word.length()).trim();
    }

    private int[] getLetterIndices(Letter letter) {
        StringBuilder sb = new StringBuilder();
        char[] targetWordChars = targetWord.toCharArray();

        for (int i = 0; i < targetWordChars.length; i++) {
            if (targetWordChars[i] == letter.getLetter().toCharArray()[0]) {
                sb.append(Integer.valueOf(i * 2)).append(' ');
            }
        }

        return Arrays.stream(sb.toString().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private String replaceUnderscores(String currentWordState, Letter letter, int[] positionsToReplace) {

        char[] word = currentWordState.toCharArray();
        for (int i : positionsToReplace) {
            word[i] = letter.getLetter().toCharArray()[0];
        }
        return new String(word);
    }
}
