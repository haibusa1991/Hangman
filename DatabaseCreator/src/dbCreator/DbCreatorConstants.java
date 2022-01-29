package dbCreator;

import java.util.ArrayList;
import java.util.List;

public class DbCreatorConstants extends CsvGenerator {
    public static final List<String> DB_CREATOR_EASY_WORD_MASKS = new ArrayList<>() {
        {
            add("...а.");
            add("....а.");
            add(".а....");
            add(".а.....");
            add("........а");
        }
    };

    public static final List<String> DB_CREATOR_MEDIUM_WORD_MASKS = new ArrayList<>() {
        {
            add("...р....");
            add("...р..");
            add("...т.....");
            add("...м...");
            add(".к........");
        }
    };

    public static final List<String> DB_CREATOR_HARD_WORD_MASKS = new ArrayList<>() {
        {
            add("...й.");
            add(".ф.....");
            add("..ж...");
            add(".......з.");
            add("...ц.....");
            add(".....ъ.");
            add(".....я");
            add("...ю..");
            add("..ю...");
        }
    };

    public static final String ACTIONS_WINDOW_TITLE = "DB creator";
    public static final String ACTIONS_WINDOW_QUESTION = "Choose an action";
    public static final String ACTIONS_COMPILE_CSVS = "Compile CSVs";
    public static final String ACTIONS_COMPILE_DB = "Compile database";

    public static final String CSV_FILE_DELIMITER = ",";
    public static final String CSV_FILENAME = "words";
    public static final String CSV_FILE_EXTENSION = ".csv";

    public final static String FILE_NAMING_WRONG = "Invalid filenames or no files were found. " +
            "Files must be named \"" + CSV_FILENAME + "#" + CSV_FILE_EXTENSION +
            "\" where # is an integer. Case insensitive.\nTry putting all .csv files into " +
            "a new directory along with the .jar file. and verify the naming pattern.";
}
