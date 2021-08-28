package hangman.LogicController;

import java.util.ArrayList;
import java.util.List;

//remove default settings from here whe updating the SettingsFileHandler
public class Settings {
    public List<Boolean> difficulty = new ArrayList<>() {{
        add(true);
        add(false);
        add(false);
    }};
    public boolean isOnline = true;
    public boolean doesSaveOnExit = true;
}
