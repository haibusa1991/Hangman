package com.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineWordsFetcher {
    private final String CSV_DELIMITER = "|";

    private final HttpClient httpClient;
    private Map<String, String> pairs;

    public OnlineWordsFetcher(String wordMask) {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        pairs = new LinkedHashMap<>();
        fetchPairs(wordMask);
    }

    public String getWordsAsCsv() {
        return getWordsAsCsv(pairs);
    }

    public boolean writeWordsAsCsvToDisk(String filename) {
        String csv = getWordsAsCsv();
        try {
            writePairsAsCsv(csv, filename);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public Map<String,String> getWordsAsMap(){
        return this.pairs;
    }

    private void fetchPairs(String wordMask) {
        HttpRequest request = buildHttpRequest(wordMask);
        HttpResponse<String> response = getResponse(request);
        List<String> rawPairs = getRawPairs(response);
        pairs = getSanitizedPairs(rawPairs);
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

    private Map<String, String> getSanitizedPairs(List<String> rawPairs) {
        Map<String, String> pairs = new LinkedHashMap<>();
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
            pairs.put(word, description);
        }
        return pairs;
    }

    private String getWordsAsCsv(Map<String, String> pairs) {
        StringBuilder words = new StringBuilder();
        for (Map.Entry<String, String> pair : pairs.entrySet()) {
            String word = pair.getKey();
            String description = pair.getValue();
            words.append(word)
                    .append(CSV_DELIMITER)
                    .append(description)
                    .append(System.lineSeparator());
        }
        return words.toString();
    }

    private void writePairsAsCsv(String csv, String filename) throws IOException {
        File csvFile = new File(dbUtils.getCsvPath() + "\\" + filename);
        FileOutputStream fos = new FileOutputStream(csvFile);
        fos.write(csv.getBytes(StandardCharsets.UTF_8));
        fos.close();
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}