package com;

public class GfxGeneratorConstants {

    //preview frame constants
    public static int FRAME_HEIGHT = 800;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_CYCLE_TIME = 250;
    public static int FRAME_HOLD_TIME = 500;

    //file names related stuff
    public static String GFX_FILE_NAME = "gfx.dat";
    public static String FILENAME_BEGINS_WITH = "hanged";
    public static String FILENAME_ENDS_WITH = ".png";

    //errors
    public static String FILE_NAMING_WRONG = "Invalid filenames or no files were found. Files must be named \"hanged#.png\" where # is an integer. Case insensitive." +
            "\nTry putting all .png files into a new directory along with the .jar file.";
    public static String EMPTY_DIRECTORY = "No files in target directory - ";

    //messages
    public static String YES_NO_SHOW_PREVIEW_QUESTION = "File generation successful. Show preview?";
    public static String YES_NO_SHOW_PREVIEW_TITLE = "Success";


}
