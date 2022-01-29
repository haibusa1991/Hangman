package dbCreator;

import com.dialogs.ErrorDialog;

import javax.swing.*;

import static dbCreator.DbCreatorConstants.*;
import static dbCreator.DbCreatorConstants.ACTIONS_COMPILE_DB;

public class DbLogicController extends DbCreator {

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


    public void generateCsvs() {
        new CsvGenerator().generateCsvs();
    }

    public void compileDb() {
        new DbCreatorDbHandler().generateDbs();
    }

    public static void throwError(String message) {
        new ErrorDialog(message);
        System.exit(-1);
    }
}
