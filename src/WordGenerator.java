import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordGenerator {
    private static WordGenerator generator = null;
    private boolean isOnlineMode = false;


    private WordGenerator() throws IOException, InterruptedException {
//        sendWordRequest();
    }

    public static WordGenerator getInstance() throws IOException, InterruptedException {
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

    public boolean isOnline() {
        return canConnect();
    }

    //bruv...
    //TODO: Replace with ping to remote site
    private boolean canConnect() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://www.funland.bg/search-words/").openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (Exception e) {
            return false;
        }
    }

    private String sendWordRequest() throws IOException, InterruptedException {
        //Online page
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        String wordRequestMask = "..й..";//words request goes here
        HttpRequest request = HttpRequest.newBuilder()
//                .POST(HttpRequest.BodyPublishers.ofString("fw=" + URLEncoder.encode(wordRequestMask, StandardCharsets.UTF_8)))
                .POST(HttpRequest.BodyPublishers.ofString("fw=" + wordRequestMask))
                .uri(URI.create("https://www.funland.bg/search-words/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Matcher matcher = Pattern.compile("<td><.*>.*<\\/td>\\R<td>.*<\\/td>").matcher(response.body());

//        //Local test page
//        String response = new String(Files.readAllBytes(Paths.get(Helpers.getResourcesPath() + "\\testpage.html")));
//        Matcher matcher = Pattern.compile("<td><a href=.*>.*</td>\\n<td>.*</td>").matcher(response);

        List<Word> wordsFound = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group().split("\n")[0]
                    .replace("<td>", "")
                    .replace("</a></td>", "");
            word = word.substring(word.indexOf('>') + 1);

            String description = matcher.group().split("\n")[1]
                    .replace("<td>", "")
                    .replace("</td>", "");
            wordsFound.add(new Word(word, description));
        }
        if (wordsFound.isEmpty()) {
            return "Error fetching word.";
        }
        return wordsFound.get(new Random().nextInt(wordsFound.size())).toString();
    }

    public String getRandomWord() throws IOException, InterruptedException {
        return sendWordRequest();
    }
}
