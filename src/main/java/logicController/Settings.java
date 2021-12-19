package logicController;

import java.io.Serializable;

public class Settings implements Serializable {
    public Difficulty difficulty = Difficulty.MEDIUM;
    public boolean isOnline = true;
    public boolean doesSaveOnExit = true;
}