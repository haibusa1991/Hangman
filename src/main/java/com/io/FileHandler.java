package com.io;

import com.game.frameController.ErrorDialog;
import com.game.frameController.FrameController;
import com.gfxController.GfxPack;
import com.logicController.LogicController;
import com.logicController.Settings;
import com.strings.Filenames;
import com.utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class FileHandler implements Serializable {

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


    private GfxPack loadHangmanGraphics() {
        try {
            //todo refactor and introduce error handler - some class that is responsible for handling all exceptions like throwing errors, prompts, messages, geneating new settings file and so on;

            InputStream graphics = this.getClass().getResourceAsStream("/resources/gfx.dat");
            ObjectInputStream ois = new ObjectInputStream(new InflaterInputStream(graphics));
            GfxPack pack = (GfxPack) ois.readObject();
            ois.close();
            graphics.close();
            return pack;
        } catch (StreamCorruptedException exc) {
            exc.printStackTrace();
            try {
                FrameController.getInstance().throwError("File gfx.dat is corrupted.");
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                FrameController.getInstance().throwError("File gfx.dat is missing or inaccessible.");
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            return null;
        }
    }


    public List<BufferedImage> loadGfx() {
        List<BufferedImage> output = new ArrayList<>();
        for (Map.Entry<Integer, byte[]> byteImage : loadHangmanGraphics().getContents().entrySet()) {
            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteImage.getValue()));
                output.add(image);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }

    public BufferedImage getApplicationIcon() {
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
