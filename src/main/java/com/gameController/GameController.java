package com.gameController;

import com.enums.Difficulty;
import com.frames.GameFrame;
import com.interfaces.HangmanGame;
import com.logicController.GraphicsManager;
import com.strings.GameFrameStrings;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.strings.GameFrameStrings.*;

public class GameController {

    private final GraphicsManager graphicsManager;
    private final GameFrame gameFrame;
    private GameState gameState;

    private String targetWord = null;
    private Iterator<BufferedImage> hangingSteps = null;
    private boolean isDead = false;
    private boolean hasWon = false;

    private Set<Character> usedLetters;

    public GameController(GameFrame gameFrame, GraphicsManager graphicsManager) {
        this.gameFrame = gameFrame;
        this.graphicsManager = graphicsManager;
    }

    public void letterClick(Letter letter) {
        this.gameState=this.gameFrame.getGameState();

        extractUsedLetters();

        if (isGoodGuess(letter)) {
            revealLetter(letter);
        } else {
            punishPlayer();
        }

        addGuessToUsedLetters(letter);

        checkIfGameHasEnded();

        if(isDead){
            this.gameState.setWordState(revealWord(this.targetWord));
        } else if(hasWon){
            this.gameState.setCurrentStep(this.graphicsManager.getWonGameImage());
        }

        if (isDead || hasWon) {
            gameFrame.disableLetters();
        }

        updateUsedLettersRepresentation();

        this.gameFrame.setGameState(this.gameState);
    }

    private void updateUsedLettersRepresentation() {
        String letters =  usedLetters.stream().map(e->e + "").collect(Collectors.joining(GAME_FRAME_LETTER_DELIMITER));
        this.gameState.setUsedLetters(GAME_FRAME_LETTERS_USED + letters);
    }

    private void checkIfGameHasEnded() {
        if (!this.gameState.getWordState().contains(GAME_FRAME_HIDDEN_LETTER)) {
            this.hasWon = true;
        } else if (!hangingSteps.hasNext()) {
            isDead = true;
        }
}

    private void punishPlayer() {
        this.gameState.setCurrentStep(hangingSteps.next());
    }

    private boolean isGoodGuess(Letter letter) {
        Character c = letter.getLetter().toUpperCase().charAt(0);

        return this.targetWord.contains(c + "")
                && !this.usedLetters.contains(c);
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
        this.isDead = false;
        this.hasWon = false;
        this.usedLetters = new LinkedHashSet<>();

        this.gameState = new GameState(
                maskWord(hangmanGame.getWord()),
                hangmanGame.getWordDescription(),
                hangmanGame.getFirstStep(),
                GameFrameStrings.GAME_FRAME_LETTERS_USED
        );

        this.gameFrame.enableLetters();
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

    private void extractUsedLetters() {
        String letters = this.gameFrame
                .getGameState()
                .getUsedLetters()
                .substring(GAME_FRAME_LETTERS_USED.length())
                .toUpperCase()
                .replace(GAME_FRAME_LETTER_DELIMITER, "");

        for (Character letter : letters.toCharArray()) {
            this.usedLetters.add(letter);
        }
    }

    private void revealLetter(Letter letter) {
        int[] positions = getLetterIndices(letter);
        this.gameState.setWordState(replaceUnderscores(this.gameState.getWordState(), letter, positions));
    }

    private void addGuessToUsedLetters(Letter letter){
        this.usedLetters.add(letter.getLetter().toUpperCase().charAt(0));
    }
}
