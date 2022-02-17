package dbCreator;

import com.gameController.Word;
import com.logicController.WordList;

import java.io.*;
import java.util.*;
import java.util.zip.DeflaterOutputStream;

import static dbCreator.DbCreatorConstants.*;

public class DbCreatorFileHandler {

    public void writeCsvToDisk(String csvContent) {
        try {
            FileOutputStream fos = new FileOutputStream(getFilenameAndPath());
            fos.write(csvContent.getBytes());
        } catch (Exception e) {
            DbLogicController.throwError(e.getMessage());
        }

    }

    private String getFilenameAndPath() {
        String[] directoryContents = new File(getCurrentPath()).list();

        assert directoryContents != null;
        int lastCsvNum = Arrays.stream(directoryContents)
                .filter(e -> e.startsWith(CSV_FILENAME) && e.endsWith(CSV_FILE_EXTENSION))
                .map(this::getCsvNumber)
                .mapToInt(e -> e)
                .max()
                .orElse(-1);

        return getCurrentPath() + CSV_FILENAME + (lastCsvNum + 1) + CSV_FILE_EXTENSION;
    }

    public String getCurrentPath() {
        String filepath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        StringBuilder sb = new StringBuilder(filepath.substring(filepath.indexOf(":/") + 2));

        while (sb.charAt(sb.length() - 1) != '/') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private int getCsvNumber(String csvFilename) {
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

    public String[] getLastThreeCsvs() {

        String[] directoryContents = new File(getCurrentPath()).list();
        assert directoryContents != null;

        String[] csvFilenames = Arrays.stream(directoryContents)
                .filter(e -> e.startsWith(CSV_FILENAME) && e.endsWith(CSV_FILE_EXTENSION))
                .sorted(Comparator.comparing(this::getCsvNumber).reversed())
                .limit(3)
                .sorted(Comparator.comparing(this::getCsvNumber))
                .map(e -> e = getCurrentPath() + e)
                .toArray(String[]::new);

        List<String> csvContents = new ArrayList<>();

        for (String filename : csvFilenames) {
            try {
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new FileReader(filename));
                br.lines().forEach(e -> {
                    sb.append(e);
                    sb.append(System.lineSeparator());
                });
                csvContents.add(sb.toString());
            } catch (Exception e) {
                DbLogicController.throwError(e.getMessage());
            }
        }

        return csvContents.toArray(String[]::new);
    }

    public void writeDbToDisk(WordList wordList, String filename) {
        String targetPath = getCurrentPath() + filename;

        try {
            FileOutputStream fos = new FileOutputStream(targetPath);
            ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
            oos.writeObject(wordList);
            oos.close();
        } catch (Exception e) {
            DbLogicController.throwError(e.toString());
        }
    }
}
