package com;

import com.dialogs.ErrorDialog;
import com.logicController.GraphicsPackage;

import javax.swing.*;
import java.io.Serializable;

import static com.GfxGeneratorConstants.*;

public class GfxGenerator implements Serializable {

    public static void main(String[] args) throws InterruptedException {
        GfxGeneratorFileHandler fh = new GfxGeneratorFileHandler();

        GraphicsPackage graphics = fh.readImagesFromDisk();
        fh.saveGraphicsPackageToDisk(graphics);


        int result = JOptionPane.showConfirmDialog(null,
                YES_NO_SHOW_PREVIEW_QUESTION,
                YES_NO_SHOW_PREVIEW_TITLE,
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            PreviewFrame preview = new PreviewFrame(graphics);
            preview.showFrame();
            preview.cycleImages();
            preview.hideFrame();
        }
    }

    public static void throwError(String message){
        new ErrorDialog(message);
        System.exit(-1);
    }
}


