package com.logicController;

import com.strings.ErrorMessages;
import com.strings.Filenames;

import javax.swing.*;
import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import static com.strings.Filenames.*;

public class FileHandler {

    public void writeSettingsToDisk(Settings settings) throws IOException {
        String filename = SETTINGS_FILENAME;
        FileOutputStream fos = new FileOutputStream(filename);
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

        InputStream graphics = this.getClass().getResourceAsStream(Filenames.GRAPHICS_FILENAME);
        if (graphics == null) {
            throw new IllegalStateException(ErrorMessages.GFX_FILE_NOT_FOUND);
        }

        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new InflaterInputStream(graphics));
        } catch (StreamCorruptedException e1) {
            throw new StreamCorruptedException(ErrorMessages.GFX_FILE_CORRUPTED);
        } catch (IOException e2) {
            throw new IOException(ErrorMessages.GFX_FILE_INACCESSIBLE);
        }

        GraphicsPackage graphicsPackage;
        try {
            graphicsPackage = (GraphicsPackage) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(ErrorMessages.GFX_FILE_INTERNAL_ERROR);
        }

        ois.close();
        graphics.close();
        return graphicsPackage;
    }

    public ImageIcon readWindowIconFromDisk() throws IOException {
        InputStream stream;
        stream = this.getClass().getResourceAsStream(GRAPHICS_WINDOW_ICON);
        ImageIcon icon;
        try {
            icon = new ImageIcon(stream.readAllBytes());
        } catch (IOException e) {
            throw new IOException(ErrorMessages.UTILS_CANNOT_LOAD_ICON_IMAGE);
        }
        return icon;
    }
}
