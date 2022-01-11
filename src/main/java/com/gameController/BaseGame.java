package com.gameController;

import com.enums.Difficulty;
import com.interfaces.HangmanGame;
import com.logicController.GraphicsManager;
import com.logicController.LogicController;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseGame implements HangmanGame {
    private final GraphicsManager graphicsManager;
    private final List<BufferedImage> imagesPlaying;
    private Word currentWord;


    // framesPlaying is an array containing the indexes of the images displayed while making a wrong guess
    // Number of frames equals number of guesses before getting hanged. All images are contained in the
    // gfx.dat file and are GraphicsPackage object.
    // eg [1,2,3,4] will display frames 1 to 4 and takes 4 wrong guesses to lose the game

    public BaseGame(GraphicsManager graphicsManager, Integer[] framesPlaying, Difficulty difficulty) {
        this.graphicsManager = graphicsManager;
        imagesPlaying = new ArrayList<>();
        initializeImagesPlaying(framesPlaying);
        setWord(difficulty);

    }

    private void setWord(Difficulty difficulty) {
        LogicController lc = LogicController.getInstance();

        switch (difficulty) {
            case EASY -> this.currentWord = lc.getEasyWord();
            case MEDIUM -> this.currentWord = lc.getMediumWord();
            case HARD -> this.currentWord = lc.getHardWord();
        }
    }

    private void initializeImagesPlaying(Integer[] framesPlaying) {
        for (Integer index : framesPlaying) {
            this.imagesPlaying.add(this.graphicsManager.getImage(index));
        }
    }

    @Override
    public Iterator<BufferedImage> iterator() {
        return new Iterator<>() {
            private int currentImage = 0;

            @Override
            public boolean hasNext() {
                return currentImage < imagesPlaying.size() - 1;
            } // -1 because last frame is not a play frame

            @Override
            public BufferedImage next() {
                currentImage++;
                return imagesPlaying.get(currentImage);
            }
        };
    }

    @Override
    public String getWordDescription() {
        return this.currentWord.getDescription();
    }

    @Override
    public String getWord() {
        return this.currentWord.getWord();
    }

    @Override
    public BufferedImage getFirstStep() {
        return this.imagesPlaying.get(0);
    }
}
