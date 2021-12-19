

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;


import java.io.File;
import java.util.List;

public class DatabaseCreator {
    public static void main(String[] args) {
//        createDBfile();
        OnlineWordsFetcher owf = new OnlineWordsFetcher();
        CsvData data = owf.getWordsAsCsv("..ф..");
    }

    private static void createDBfile() {
        EnvironmentConfig environmentConfig = new EnvironmentConfig();
        environmentConfig.setAllowCreate(true);
        try {
            Environment dbEnv = new Environment(new File(dbUtils.getdbOutputPath()), environmentConfig);
            System.out.println("Created db at " + dbUtils.getdbOutputPath());
        } catch (
                DatabaseException e) {
            e.printStackTrace();
        }
    }
}