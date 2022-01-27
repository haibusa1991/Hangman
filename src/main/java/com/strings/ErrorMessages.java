package com.strings;

import static com.strings.Filenames.*;

public class ErrorMessages {
    public static final String GFX_FILE_NOT_FOUND = "File " + GRAPHICS_FILENAME + " cannot be found.";
    public static final String GFX_FILE_CORRUPTED = "File " + GRAPHICS_FILENAME + " is corrupted.";
    public static final String GFX_FILE_INACCESSIBLE = "File " + GRAPHICS_FILENAME + " is inaccessible.";
    public static final String GFX_FILE_INTERNAL_ERROR = "Internal error - GraphicsPackage object not found";

    public static final String WINDOW_CONTROLLER_CANNOT_GET_GAME_FRAME = "Unable to get game frame!";
    public static final String UTILS_CANNOT_LOAD_ICON_IMAGE = "Unable to load " + GRAPHICS_WINDOW_ICON;

}
