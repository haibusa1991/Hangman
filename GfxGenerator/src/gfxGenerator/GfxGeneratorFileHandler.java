package gfxGenerator;

import com.logicController.GraphicsPackage;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.DeflaterOutputStream;

public class GfxGeneratorFileHandler {

    public GraphicsPackage readImagesFromDisk() {
        String path = getAbsolutePngFilepath();
        List<String> images = getImagesList(path);

        GraphicsPackage pack = new GraphicsPackage();

        for (int i = 0; i < images.size(); i++) {
            try {
                FileInputStream fos = new FileInputStream(images.get(i));
                pack.add(i, fos.readAllBytes());
            } catch (Exception e) {
                GfxGenerator.throwError(e.getMessage());
            }
        }
        return pack;
    }

    private List<String> getImagesList(String path) {
        File gfxDirectory = new File(path);
        String[] files = gfxDirectory.list();
        if (files == null) {
            GfxGenerator.throwError(GfxGeneratorConstants.EMPTY_DIRECTORY + gfxDirectory);
        }

        List<String> filePaths = Arrays.stream(files)
                .filter(e -> e.startsWith(GfxGeneratorConstants.FILENAME_BEGINS_WITH) && e.endsWith(GfxGeneratorConstants.FILENAME_ENDS_WITH))
                .sorted(Comparator.comparingInt(this::sanitize))
                .map(e -> e = path + e)
                .collect(Collectors.toList());
        if (filePaths.isEmpty()) {
            GfxGenerator.throwError(GfxGeneratorConstants.FILE_NAMING_WRONG);
        }
        return filePaths;
    }

    private int sanitize(String filename) {
        StringBuilder sb = new StringBuilder(filename);

        for (int i = 0; i < GfxGeneratorConstants.FILENAME_BEGINS_WITH.length(); i++) {
            sb.deleteCharAt(0);
        }

        for (int i = 0; i < GfxGeneratorConstants.FILENAME_ENDS_WITH.length(); i++) {
            sb.deleteCharAt(sb.length() - 1);
        }

        int value=0;
        try {
            value = Integer.parseInt(sb.toString());
        } catch (Exception e) {
            GfxGenerator.throwError(GfxGeneratorConstants.FILE_NAMING_WRONG);
        }
        return value;
    }

    public void saveGraphicsPackageToDisk(GraphicsPackage pack) {

        try {
            FileOutputStream fos = new FileOutputStream(GfxGeneratorConstants.GFX_FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(new DeflaterOutputStream(fos));
            oos.writeObject(pack);
        } catch (IOException e) {
            GfxGenerator.throwError(e.getMessage());
        }
    }

    private String getAbsolutePngFilepath() {

        String filepath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        StringBuilder sb = new StringBuilder(filepath.substring(filepath.indexOf(":/") + 2));

        while (sb.charAt(sb.length() - 1) != '/') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
