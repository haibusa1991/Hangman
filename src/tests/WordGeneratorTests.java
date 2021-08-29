package tests;

import hangman.WordGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WordGeneratorTests {
    @Test
    public void canConnect() throws IOException, InterruptedException {
        WordGenerator  wg = WordGenerator.getInstance();
        boolean cc = wg.canConnect();
        assertTrue(cc);
    }
}