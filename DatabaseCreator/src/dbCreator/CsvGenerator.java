package dbCreator;

import com.logicController.OnlineWordsFetcher;
import com.gameController.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dbCreator.DbCreatorConstants.*;
import static dbCreator.DbCreatorConstants.CSV_FILE_DELIMITER;

public class CsvGenerator {
    private final Map<String, String> easyWords;
    private final Map<String, String> mediumWords;
    private final Map<String, String> hardWords;

    public CsvGenerator() {
        this.easyWords = new HashMap<>();
        this.mediumWords = new HashMap<>();
        this.hardWords = new HashMap<>();
    }

    public void generateCsvs() {
        fetchWords(DB_CREATOR_EASY_WORD_MASKS, this.easyWords);
        fetchWords(DB_CREATOR_MEDIUM_WORD_MASKS, this.mediumWords);
        fetchWords(DB_CREATOR_HARD_WORD_MASKS, this.hardWords);

        List<String> csvs = new ArrayList<>() {{
            add(buildCsv(easyWords));
            add(buildCsv(mediumWords));
            add(buildCsv(hardWords));
        }};

        for (String csv : csvs) {
            DbCreatorFileHandler fh = new DbCreatorFileHandler();
            fh.writeCsvToDisk(csv);
        }
    }

    private String buildCsv(Map<String, String> wordSet) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> word : wordSet.entrySet()) {
            sb.append(word.getKey())
                    .append(CSV_FILE_DELIMITER)
                    .append(word.getValue())
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    private void fetchWords(List<String> wordMasksSet, Map<String, String> wordSet) {
        for (String s : wordMasksSet) {
            OnlineWordsFetcher owf = new OnlineWordsFetcher(s);
            owf.run();
            List<Word> fetchedWords = owf.getWords();
            for (Word word : fetchedWords) {
                wordSet.putIfAbsent(word.getWord(), word.getDescription());
            }
        }
    }
}
