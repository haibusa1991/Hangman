package hangman.LogicController;

import java.io.Serializable;
import java.util.List;

public class Settings implements Serializable {
    public List<Boolean> difficulty;
    public boolean isOnline;
    public boolean doesSaveOnExit;
}