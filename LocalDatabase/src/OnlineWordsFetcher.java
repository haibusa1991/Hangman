
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineWordsFetcher {
    private final HttpClient httpClient;

    public OnlineWordsFetcher() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public CsvData getWordsAsCsv(String wordMask) {
        HttpRequest request = buildHttpRequest(wordMask);
        HttpResponse<String> response = getResponse(request);
        List<String> rawPairs = getRawPairs(response);
        Map<String, String> pairs = getWordDescriptionPairs(rawPairs);

        return getPairsAsCsv(pairs);
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

    private Map<String, String> getWordDescriptionPairs(List<String> rawPairs) {
        Map<String, String> pairs = new LinkedHashMap<>();
        for (String rawPair : rawPairs) {
//<td><a href="https://www.funland.bg/words/АНФАС/">АНФАС</a></td>\r\n<td>положение на лице при рисуване</td>

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

    private CsvData getPairsAsCsv(Map<String, String> pairs) {
        return null;
    }

    private String capitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}