package com.logicController;

import com.gameController.Word;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineWordsFetcher implements Runnable {

    private final HttpClient httpClient;
    private List<Word> words;

    public OnlineWordsFetcher(String wordMask) {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        words = new ArrayList<>();
        fetchPairs(wordMask);
    }


    private void fetchPairs(String wordMask) {
        HttpRequest request = buildHttpRequest(wordMask);
        HttpResponse<String> response = getResponse(request);
        List<String> rawPairs = getRawPairs(response);
        words = getSanitizedPairs(rawPairs);
    }

    private HttpRequest buildHttpRequest(String wordMask) {
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("fw=" + wordMask))
                .uri(URI.create("https://www.funland.bg/search-words/"))
                .setHeader("User-Agent", "Java HttpClient Bot") // add request header
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();
    }

    private HttpResponse<String> getResponse(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private List<String> getRawPairs(HttpResponse<String> response) {
        List<String> rawPairs = new ArrayList<>();
        Matcher matcher = Pattern.compile("<td><.*>.*<\\/td>\\R<td>.*<\\/td>").matcher(response.body());
        while (matcher.find()) {
            rawPairs.add(matcher.group());
        }
        return rawPairs;
    }

    private List<Word> getSanitizedPairs(List<String> rawPairs) {
        List<Word> words = new ArrayList<>();
        for (String rawPair : rawPairs) {

            String word = rawPair.split("\r\n")[0]
                    .replace("<td>", "")
                    .replace("</a></td>", "")
                    .split(">")[1]
                    .toUpperCase();

            String description = rawPair
                    .split("\r\n")[1]
                    .replace("<td>", "")
                    .replace("</td>", "");

            description = capitalizeFirstLetter(description);
            words.add(new Word(word, description));
        }
        return words;
    }

    public List<Word> getWords() {
        return Collections.unmodifiableList(this.words);
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    @Override
    public void run() {

    }
}