package com.logicController;

import com.enums.Difficulty;
import com.strings.ErrorMessages;

import javax.swing.*;
import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import static com.strings.Filenames.*;
import static com.strings.ErrorMessages.*;

public class FileHandler {

    public void writeSettingsToDisk(Settings settings) throws IOException {
        FileOutputStream fos = new FileOutputStream(SETTINGS_FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
        oos.writeObject(settings);
        oos.close();
        fos.close();
    }

    public Settings readSettingsFromDisk() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(SETTINGS_FILENAME);
        ObjectInputStream ois = new ObjectInputStream(new InflaterInputStream(fis));
        Object objectFromDisk = ois.readObject();

        Settings settings = null;
        if (objectFromDisk instanceof Settings) {
            settings = (Settings) objectFromDisk;
        }

        ois.close();
        fis.close();

        if (settings == null) {
            throw new StreamCorruptedException();
        }
        return settings;
    }


    public GraphicsPackage readGraphicsFromDisk() throws IOException, ClassNotFoundException {

        InputStream graphics = this.getClass().getResourceAsStream(GRAPHICS_FILENAME);
        if (graphics == null) {
            throw new IllegalStateException(GFX_FILE_NOT_FOUND);
        }

        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new InflaterInputStream(graphics));
        } catch (StreamCorruptedException e1) {
            throw new StreamCorruptedException(GFX_FILE_CORRUPTED);
        } catch (IOException e2) {
            throw new IOException(GFX_FILE_INACCESSIBLE);
        }

        GraphicsPackage graphicsPackage;
        try {
            graphicsPackage = (GraphicsPackage) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(GFX_FILE_INTERNAL_ERROR);
        }

        ois.close();
        graphics.close();
        return graphicsPackage;
    }

    public ImageIcon readWindowIconFromDisk() throws IOException {
        InputStream stream;
        stream = this.getClass().getResourceAsStream(GRAPHICS_WINDOW_ICON);
        ImageIcon icon = null;
        try {
            if (stream != null) {
                icon = new ImageIcon(stream.readAllBytes());
            }
        } catch (IOException e) {
            throw new IOException(ErrorMessages.UTILS_CANNOT_LOAD_ICON_IMAGE);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return icon;
    }


    public WordList readDbFromDisk(Difficulty difficulty) {
        String dbFilename = null;

        switch (difficulty) {
            case EASY -> dbFilename = RESOURCES_PATH + EASY_WORDS_DB_FILENAME;
            case MEDIUM -> dbFilename = RESOURCES_PATH + MEDIUM_WORDS_DB_FILENAME;
            case HARD -> dbFilename = RESOURCES_PATH + HARD_WORDS_DB_FILENAME;
            default -> LogicController.throwError(ErrorMessages.LOCAL_DB_FILE_NOT_FOUND);
        }

        InputStream is = this.getClass().getResourceAsStream(dbFilename);

        ObjectInputStream ois = null;
        try {
            if (is != null) {
                ois = new ObjectInputStream(new InflaterInputStream(is));
            }
        } catch (IOException e) {
            LogicController.throwError(LOCAL_DB_IO_ERROR);
        }

        Object readObject = null;
        try {
            if (ois != null) {
                readObject = ois.readObject();
            }
        } catch (IOException e) {
            LogicController.throwError(LOCAL_DB_IO_ERROR);
        } catch (ClassNotFoundException e) {
            LogicController.throwError(LOCAL_DB_CLASS_NOT_FOUND);
        }

        if (readObject instanceof WordList) {
            return (WordList) readObject;
        }

        return null;
    }

}
