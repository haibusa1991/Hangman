package dbCreator;


import com.gameController.Word;
import com.logicController.WordList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.strings.Filenames.*;

public class DbCreatorDbHandler extends DbCreatorConstants {
    private Map<String, String> easyWords;
    private Map<String, String> mediumWords;
    private Map<String, String> hardWords;

    public DbCreatorDbHandler() {
        this.easyWords = new HashMap<>();
        this.mediumWords = new HashMap<>();
        this.hardWords = new HashMap<>();
    }


    public void generateDbs() {
        DbCreatorFileHandler fileHandler = new DbCreatorFileHandler();
        String[] csvContents = fileHandler.getLastThreeCsvs();

        populateMap(csvContents[0], this.easyWords);
        generateDbAndSaveToDisk(this.easyWords, EASY_WORDS_DB_FILENAME);

        populateMap(csvContents[1], this.mediumWords);
        generateDbAndSaveToDisk(this.mediumWords, MEDIUM_WORDS_DB_FILENAME);

        populateMap(csvContents[2], this.hardWords);
        generateDbAndSaveToDisk(this.hardWords, HARD_WORDS_DB_FILENAME);
    }

    private void populateMap(String csvContent, Map<String, String> map) {
        String[] words = csvContent.split(System.lineSeparator());

        for (String word : words) {
            String currentWord = word.split(CSV_FILE_DELIMITER)[0];
            String currentDescription = word.split(CSV_FILE_DELIMITER)[1];
            map.putIfAbsent(currentWord, currentDescription);
        }

    }

    private void generateDbAndSaveToDisk(Map<String, String> csv, String filename) {
        WordList wordList = new WordList();
        for (Map.Entry<String, String> entry : csv.entrySet()) {
            wordList.add(new Word(entry.getKey(), entry.getValue()));
        }

        new DbCreatorFileHandler().writeDbToDisk(wordList,filename);
    }
}
