package com.interfaces;

import java.awt.image.BufferedImage;

public interface HangmanGame extends Iterable<BufferedImage> {

    String getWordDescription();

    String getWord();

    BufferedImage getFirstStep();

}
