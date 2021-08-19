import java.io.IOException;

public class FrameUpdater extends Thread{
    public void run(){
        while (true){
            try {
                FrameController.updateOnlineStatus(WordGenerator.getInstance().isOnline());
                sleep(1000);
            } catch (InterruptedException | IOException e) {
                break;
            }
        }
    }
}
