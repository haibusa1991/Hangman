package com.database;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;


import java.io.File;
import java.util.Map;

public class DatabaseCreator {
    public static void main(String[] args) {
//        initializeEnvironment();
//        OnlineWordsFetcher owf = new OnlineWordsFetcher("..ф..");
//        String csv = owf.getWordsAsCsv();
//        owf.writeWordsAsCsvToDisk("csv1.csv");
//        Map<String, String> words = owf.getWordsAsMap();
//        System.out.println();
    }

    private static void initializeEnvironment() {
        Environment dbEnv = null;

        try {
            EnvironmentConfig envConf = new EnvironmentConfig();
            envConf.setAllowCreate(true);
            dbEnv = new Environment(new File(dbUtils.getdbOutputPath()), envConf);
            System.out.println("Initialized db at " + dbUtils.getdbOutputPath());
        } catch (
                DatabaseException e) {
            e.printStackTrace();
        }

    }
}