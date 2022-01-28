package dbCreator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import static dbCreator.DbCreatorConstants.*;

public class DbCreatorFileHandler {

    public void writeCsvToDisk(String csvContent) throws IOException {
        FileOutputStream fos = new FileOutputStream(getFilenameAndPath());
        //todo write exception handling so the write stream is flushed and  closed on error
        fos.write(csvContent.getBytes());

    }

    private String getFilenameAndPath() {
        String[] directoryContents = new File(getCurrentPath()).list();

        int lastCsvNum = Arrays.stream(directoryContents) //todo fix warning
                .filter(e -> e.startsWith(CSV_FILENAME) && e.endsWith(CSV_FILE_EXTENSION))
                .map(this::getCsvNumber)
                .mapToInt(e->e)
                .max()
                .orElse(-1);

        return getCurrentPath() + CSV_FILENAME + (lastCsvNum+1) + CSV_FILE_EXTENSION;
    }

    private String getCurrentPath() { //this works
        String filepath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        StringBuilder sb = new StringBuilder(filepath.substring(filepath.indexOf(":/") + 2));

        while (sb.charAt(sb.length() - 1) != '/') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private int getCsvNumber(String csvFilename) { //this works as well
        StringBuilder sb = new StringBuilder(csvFilename);

        for (int i = 0; i < CSV_FILENAME.length(); i++) {
            sb.deleteCharAt(0);
        }

        for (int i = 0; i < CSV_FILE_EXTENSION.length(); i++) {
            sb.deleteCharAt(sb.length() - 1);
        }

        int value = 0;
        try {
            value = Integer.parseInt(sb.toString());
        } catch (Exception e) {
            DbLogicController.throwError(FILE_NAMING_WRONG);
        }
        return value;
    }

    //todo write a read from disk method that reads the last 3 .csv files. Will be required for the
    // database creator module.
}
