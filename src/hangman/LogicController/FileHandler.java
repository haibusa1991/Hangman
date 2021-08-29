package hangman.LogicController;

import hangman.FrameController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class FileHandler implements Serializable {

    public void saveSettings(Settings settings) {
        try {
            FileOutputStream fos = new FileOutputStream("settings.dat");
            ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
            oos.writeObject(settings);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Settings loadSettings() {
        try {
            FileInputStream fis = new FileInputStream("settings.dat");
            ObjectInputStream ois = new ObjectInputStream(new InflaterInputStream(fis));
            Settings settings = (Settings) ois.readObject();
            ois.close();
            fis.close();
            return settings;
        } catch (Exception ex) {
            Settings settings = new Settings() {{
                difficulty = new ArrayList<>() {{
                    add(true);
                    add(false);
                    add(false);
                }};
                isOnline = true;
                doesSaveOnExit = true;
            }};
            saveSettings(settings);
            return settings;
        }
    }

    private GfxPack loadGfxFromDisk() {
        try {
            FileInputStream fis = new FileInputStream("gfx.dat");
            ObjectInputStream ois = new ObjectInputStream(new InflaterInputStream(fis));
            GfxPack pack = (GfxPack) ois.readObject();
            ois.close();
            fis.close();
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
        for (Map.Entry<Integer, byte[]> byteImage : loadGfxFromDisk().getContents().entrySet()) {
            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteImage.getValue()));
                output.add(image);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return output;
    }

}
