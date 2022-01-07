package com.logicController;

import com.main.Hangman;
import com.strings.ErrorMessages;
import com.strings.Filenames;
import com.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class FileHandler {

    public void writeSettingsToDisk(Settings settings) throws IOException {
        String filename = Utils.getArtifactPath() + Filenames.SETTINGS_FILENAME;
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
        oos.writeObject(settings);
        oos.close();
        fos.close();
    }

    public Settings readSettingsFromDisk() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(Filenames.SETTINGS_FILENAME);
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

    public GraphicsPackage readGraphicsFromDiskDebug() throws IOException, ClassNotFoundException {
        String HARDCODED_PATH = "D:\\Repos\\Hangman\\target\\classes\\com\\resources\\gfx.dat";

//        InputStream graphics = this.getClass().getResourceAsStream(HARDCODED_PATH);
        FileInputStream graphics = new FileInputStream(HARDCODED_PATH);
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

    public BufferedImage reaApplicationIconFromDisk() {
        try {
            InputStream image = this.getClass().getResourceAsStream("/resources/icon.png");
            return ImageIO.read(image);
        } catch (IOException e) {
            //todo refactor and introduce error handler - some class that is responsible for handling all exceptions like throwing errors, prompts, messages, geneating new settings file and so on;
            new ErrorDialog("Unable to read icon.png. File is missing or corrupted");
            LogicController.getInstance().terminateApp();
        }
        return null;
    }

}
