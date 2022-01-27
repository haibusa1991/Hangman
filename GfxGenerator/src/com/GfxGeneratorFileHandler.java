package com;

import com.dialogs.ErrorDialog;
import com.logicController.FileHandler;
import com.logicController.GraphicsPackage;

import java.io.*;
import java.util.zip.DeflaterOutputStream;

import static com.GfxGeneratorConstants.*;

public class GfxGeneratorFileHandler {

    public GraphicsPackage readImagesFromDisk() {
        GraphicsPackage pack = new GraphicsPackage();
        try {
            for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
                String filename = PNG_PATH + PNG_FILE_NAME + i + PNG_FILE_EXTENSION;

                InputStream fos = this.getClass().getResourceAsStream(filename);

                byte[] image = new byte[0]; //added initialization and if check because intellij nags about null pointers
                if (fos != null) {
                    image = fos.readAllBytes();
                }

                pack.add(i, image);
            }
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
        }
        return pack;
    }

    public void saveGraphicsPackageToDisk(GraphicsPackage pack) {
        String pngTargetPath = "gfx.dat";

        try {
            FileOutputStream fos = new FileOutputStream(pngTargetPath);
            ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
            oos.writeObject(pack);
        } catch (IOException e) {
            new ErrorDialog(e.getMessage());
        }
    }
}
