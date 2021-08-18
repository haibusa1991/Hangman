import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyFrame frame = MyFrame.getInstance();
        FrameController controller = FrameController.getInstance();
        WordGenerator wordGenerator = WordGenerator.getInstance();
        wordGenerator.setOnlineMode();
    }
}
