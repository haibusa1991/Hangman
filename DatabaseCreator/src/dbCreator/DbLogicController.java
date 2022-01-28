package dbCreator;

import com.database.OnlineWordsFetcher;
import com.dialogs.ErrorDialog;
import com.gameController.Word;

import javax.swing.*;

import java.io.IOException;
import java.util.*;

import static dbCreator.DbCreatorConstants.*;
import static dbCreator.DbCreatorConstants.ACTIONS_COMPILE_DB;

public class DbLogicController {
    private final Map<String, String> easyWords;
    private final Map<String, String> mediumWords;
    private final Map<String, String> hardWords;

    public DbLogicController() {
        this.easyWords = new LinkedHashMap<>();
        this.mediumWords = new LinkedHashMap<>();
        this.hardWords = new LinkedHashMap<>();
    }

    public int getAction() {
        return JOptionPane.showOptionDialog(null,
                ACTIONS_WINDOW_QUESTION,
                ACTIONS_WINDOW_TITLE,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{ACTIONS_COMPILE_CSVS, ACTIONS_COMPILE_DB},
                -1
        );
    }

    public void generateCsvs() throws IOException { //todo handle exception in this class and show error dialog.
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
            List<Word> fetchedWords = owf.getWords();
            for (Word word : fetchedWords) {
                wordSet.putIfAbsent(word.getWord(), word.getDescription());
            }
        }
    }

    public void compileDb() {
        System.out.println("compiling db...");
        //todo - implement
        // should read all contents from the last 3 .csv files and then
        // create 3 new databases for easy, medium and hard words respectively.
    }

    public static void throwError(String message) {
        new ErrorDialog(message);
        System.exit(-1);
    }

}
