package edu.project3.analizators;

import edu.project3.NginxBody;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static edu.project3.Report.create1x2row;

public class Resourses {
    private Resourses() {
    }

    private static final int FIVE = 5;

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
            .limit(FIVE)
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
        return (raws.length == FIVE)
            ? raws[1]
            : null;
    }
}
