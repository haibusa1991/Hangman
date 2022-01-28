package gfxGenerator;

import com.dialogs.ErrorDialog;
import com.logicController.GraphicsPackage;
import com.utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static gfxGenerator.GfxGeneratorConstants.*;

public class PreviewFrame {
    private final List<BufferedImage> loadedImages;
    private JFrame frame;
    JLabel currentImage;

    public PreviewFrame(GraphicsPackage images) {
        loadedImages = new ArrayList<>();
        loadImages(images);
        initFrame();
    }

    public void cycleImages() throws InterruptedException {
        for (BufferedImage image : loadedImages) {
            currentImage.setIcon(new ImageIcon(image));
            frame.repaint();
            Thread.sleep(FRAME_CYCLE_TIME);
        }
        Thread.sleep(FRAME_HOLD_TIME);
    }

    private void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        currentImage = new JLabel();
        frame.add(currentImage);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        Utils.centerFrame(frame);
    }

    private void loadImages(GraphicsPackage images) {
        for (Map.Entry<Integer, byte[]> byteImage : images.getContents().entrySet()) {
            try {
                BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteImage.getValue()));
                loadedImages.add(image);
            } catch (Exception e) {
                new ErrorDialog(e.getMessage());
            }
        }
    }

    public void showFrame(){
        this.frame.setVisible(true);
    }

    public void hideFrame(){
        this.frame.dispose();
    }
}
