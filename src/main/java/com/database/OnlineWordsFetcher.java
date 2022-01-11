package com.database;

import com.gameController.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineWordsFetcher {
//    private final String CSV_DELIMITER = "|";

    private final HttpClient httpClient;
    //    private Map<String, String> pairs; //word, description
    private List<Word> words;

    public OnlineWordsFetcher(String wordMask) {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        words = new ArrayList<>();
        fetchPairs(wordMask);
    }

//    public String getWordsAsCsv() {
//        return getWordsAsCsv(pairs);
//    }
//
//    public boolean writeWordsAsCsvToDisk(String filename) {
//        String csv = getWordsAsCsv();
//        try {
//            writePairsAsCsv(csv, filename);
//            return true;
//        } catch (IOException e) {
//            return false;
//        }
//
//    }

//    public Map<String, String> getWordsAsMap() {
//        return this.pairs;
//    }

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
//        Map<String, String> pairs = new LinkedHashMap<>();
        List<Word> words = new ArrayList<>();
        for (String rawPair : rawPairs) {

            String word = rawPair.split("\r\n")[0]
                    .replace("<td>", "")
                    .replace("</a></td>", "")
                    .split(">")[1];

            String description = rawPair
                    .split("\r\n")[1]
                    .replace("<td>", "")
                    .replace("</td>", "");

            description = capitalizeFirstLetter(description);
            words.add(new Word(word, description));
        }
        return words;
    }

//    private String getWordsAsCsv(Map<String, String> pairs) {
//        StringBuilder words = new StringBuilder();
//        for (Map.Entry<String, String> pair : pairs.entrySet()) {
//            String word = pair.getKey();
//            String description = pair.getValue();
//            words.append(word)
//                    .append(CSV_DELIMITER)
//                    .append(description)
//                    .append(System.lineSeparator());
//        }
//        return words.toString();
//    }

//    private void writePairsAsCsv(String csv, String filename) throws IOException {
//        File csvFile = new File(dbUtils.getCsvPath() + "\\" + filename);
//        FileOutputStream fos = new FileOutputStream(csvFile);
//        fos.write(csv.getBytes(StandardCharsets.UTF_8));
//        fos.close();
//    }

//    public Word getRandomWord() { //todo remove this method, it's a quick and dirty
//        Random random = new Random();
//        int index = random.nextInt(this.pairs.size()) + 1;
//
//        String word = "<EMPTY>";
//        String description = "<EMPTY>";
//
//        Iterator<Map.Entry<String, String>> iterator = pairs.entrySet().iterator();
//
//        while (index-- > 0) {
//            Map.Entry<String, String> entry = iterator.next();
//            word = entry.getKey();
//            description = entry.getValue();
//        }
//
//        return new Word(word, description);
//    }


    public List<Word> getWords() {
        return Collections.unmodifiableList(this.words);
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}