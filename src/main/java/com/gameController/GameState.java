package com.gameController;

import java.awt.image.BufferedImage;

public class GameState {
    private final String description;

    private String wordState;
    private String usedLetters;

    private BufferedImage currentStep;

    public GameState(String wordState, String description, BufferedImage currentStep, String usedLetters) {
        this.wordState = wordState;
        this.description = description;
        this.currentStep = currentStep;
        this.usedLetters = usedLetters;
    }

    public String getDescription() {
        return description;
    }

    public String getWordState() {
        return wordState;
    }

    public BufferedImage getCurrentStep() {
        return currentStep;
    }

    public String getUsedLetters() {
        return this.usedLetters;
    }

    public void setWordState(String wordState) {
        this.wordState = wordState;
    }

    public void setUsedLetters(String usedLetters) {
        this.usedLetters = usedLetters;
    }

    public void setCurrentStep(BufferedImage currentStep) {
        this.currentStep = currentStep;
    }
}
