package edu.project3.analizators;

import edu.project3.NginxBody;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.project3.Report.create1x3row;

public class ResponseFreq {
    public static String analyze(List<NginxBody> logs) {
        Map<String, String> top5codes = logs.stream()
            .map(item -> Integer.toString(item.status()))
            .collect(
                Collectors.groupingBy(
                    Function.identity(), Collectors.counting()
                )
            )
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> entry.getValue().toString(),
                    (e1, e2) -> e1,
                    LinkedHashMap::new
                )
            );

        var builder = new StringBuilder();
        top5codes.forEach((status, count) ->
            builder.append(
                create1x3row(status, getStatus(status), count)
            )
        );

        return builder.toString();
    }

    private static String getStatus(String statusCode) {
        return switch (statusCode) {
            case "200" -> "OK";
            case "201" -> "Created";
            case "204" -> "No Content";
            case "400" -> "Bad Request";
            case "401" -> "Unauthorized";
            case "403" -> "Forbidden";
            case "404" -> "Not Found";
            case "500" -> "Internal Server Error";
            default -> "Unknown";
        };
    }
}
