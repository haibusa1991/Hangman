package com;

import com.logicController.GraphicsPackage;

import java.io.Serializable;

public class GfxGenerator implements Serializable {

    public static void main(String[] args) throws InterruptedException {
        GfxGeneratorFileHandler fh = new GfxGeneratorFileHandler();

        GraphicsPackage graphics = fh.readImagesFromDisk();
        fh.saveGraphicsPackageToDisk(graphics);

        PreviewFrame preview = new PreviewFrame(graphics);
        preview.showFrame();
        preview.cycleImages();
        preview.hideFrame();
    }

}


