package edu.project3.analizators;

import edu.project3.NginxBody;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.project3.Report.create1x2row;

public class Resourses {
    public static String analyze(List<NginxBody> nginxLogItems) {
        Map<String, String> top5resources = nginxLogItems.stream()
            .map(NginxBody::request)
            .map(Resourses::parseResource)
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

        var res = new StringBuilder();
        top5resources.forEach((resource, count) ->
            res.append(create1x2row(resource, count))
        );

        return res.toString();
    }

    private static String parseResource(String request) {
        String[] raws = request.split(" ");
        return (raws.length == 5)
            ? raws[1]
            : null;
    }
}
