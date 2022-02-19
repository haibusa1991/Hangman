package com.logicController;

import com.gameController.Word;
import com.strings.ErrorMessages;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.strings.ErrorMessages.*;

public class WordsFetcher {

    private final HttpClient httpClient;
    private String wordmask;
    private List<Word> words;

    public WordsFetcher(String wordmask) {
        this.wordmask = wordmask;
        words = new ArrayList<>();

        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public void setWordmask(String wordmask) {
        this.wordmask = wordmask;
    }


    public void fetchPairs() {
        HttpRequest request = buildHttpRequest(this.wordmask);
        HttpResponse<String> response = getResponse(request);
        List<String> rawPairs = getRawPairs(response);
        this.words = getSanitizedPairs(rawPairs);
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
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(WORD_FETCHER_REMOTE_SERVER_NOT_RESPONDING);
            return null;
        }
        return response;
    }

    private List<String> getRawPairs(HttpResponse<String> response) {
        List<String> rawPairs = new ArrayList<>();
        if(response==null){
            return rawPairs;
        }
        Matcher matcher = Pattern.compile("<td><.*>.*</td>\\R<td>.*</td>").matcher(response.body());
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
        if (this.words.isEmpty()) {
            throw new IllegalStateException(ErrorMessages.WORD_FETCHER_WORDLIST_IS_EMPTY);
        }
        return Collections.unmodifiableList(this.words);
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}