package com;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCreatorConstants {
    public static List<String> DB_CREATOR_WORD_MASKS = new ArrayList<>() {
        {
            //easy words
            add("...а.");
            //medium words
            add("...р....");
            //hard words
            add("...й.");
        }
    };
}
