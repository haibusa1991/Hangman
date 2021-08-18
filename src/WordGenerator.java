import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordGenerator {
    private static WordGenerator generator = null;
    private boolean isOnlineMode = false;


    private WordGenerator() {

    }

    public static WordGenerator getInstance() {
        if (generator == null) {
            generator = new WordGenerator();
        }
        return generator;
    }

    public boolean IsOnlineMode() {
        return isOnlineMode;
    }

    public void setOnlineMode() {
        this.isOnlineMode = canConnect();
        System.out.println(isOnlineMode);
    }

    public void setOfflineMode() {
        this.isOnlineMode = false;
    }

    public boolean isOnline(){
        return canConnect();
    }

    private boolean canConnect() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://www.funland.bg/search-words/").openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        }catch (Exception e){
            return false;
        }
    }
}
