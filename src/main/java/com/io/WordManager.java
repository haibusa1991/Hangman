package com.io;

import com.gameController.Word;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordManager {
    private static WordManager generator = null;
    private boolean isOnlineMode = false;


    private WordManager() throws IOException, InterruptedException {
//        sendWordRequest();
    }

    public static WordManager getInstance() throws IOException, InterruptedException {
        if (generator == null) {
            generator = new WordManager();
        }
        return generator;
    }

    public boolean IsOnlineMode() {
        return isOnlineMode;
    }

    public void setOnlineMode() {
//        this.isOnlineMode = canConnect();
        System.out.println(isOnlineMode);
    }

    public void setOfflineMode() {
        this.isOnlineMode = false;
    }

//    public boolean isOnline() {
//        return canConnect();
//    }

    public boolean canConnect() throws IOException {
        try {
            return InetAddress.getByName("funland.bg").isReachable(200);
        }catch (IOException e){
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
                .POST(HttpRequest.BodyPublishers.ofString("fw=" + wordRequestMask))
                .uri(URI.create("https://www.funland.bg/search-words/"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Matcher matcher = Pattern.compile("<td><.*>.*<\\/td>\\R<td>.*<\\/td>").matcher(response.body());

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
        return wordsFound.get(new Random().nextInt(wordsFound.size()-1)).toString();
    }

    public String getRandomWord() throws IOException, InterruptedException {
        return sendWordRequest();
    }
}
