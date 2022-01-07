package com.logicController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class GraphicsManager {
    private List<BufferedImage> images;
    private GraphicsPackage graphicsPackage;

    public GraphicsManager() throws IOException, ClassNotFoundException {
        graphicsPackage = new FileHandler().readGraphicsFromDisk();
        initializeImages();
    }

    private void initializeImages() {
        for (Map.Entry<Integer, byte[]> byteImage : graphicsPackage.getContents().entrySet()) {
            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteImage.getValue()));
                images.add(image);
            } catch (IOException ignored) {
            }
        }
    }

    public BufferedImage getImage(int index) {
        if (index < 0 || index >= images.size()) {
            throw new IndexOutOfBoundsException();
        }
        return images.get(index);
    }


}
