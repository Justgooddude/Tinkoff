package edu.project3.analizators;

import edu.project3.NginxBody;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.project3.Report.create1x3row;

public class HttpStatus {
    public static String analyze(List<NginxBody> nginxLogItems) {
        Map<String, Long> statusCounter = nginxLogItems.stream()
            .collect(Collectors.groupingBy(
                HttpStatus::getStatusCategory,
                Collectors.counting()
            ));

        Locale locale = Locale.forLanguageTag("ru-RU");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        var builder = new StringBuilder();
        for (Map.Entry<String, Long> entry : statusCounter.entrySet()) {
            String category = entry.getKey();
            long count = entry.getValue();
            double percent = (count * 100.0) / nginxLogItems.size();

            builder.append(create1x3row(category, Long.toString(count), df.format(percent)));
        }

        return builder.toString();
    }

    @SuppressWarnings({"MagicNumber", "ReturnCount"})
    private static String getStatusCategory(NginxBody lof) {
        int statusCode = lof.status();

        if (statusCode >= 100 && statusCode < 200) {
            return "Informational";
        } else if (statusCode >= 200 && statusCode < 300) {
            return "Successful";
        } else if (statusCode >= 300 && statusCode < 400) {
            return "Redirection";
        } else if (statusCode >= 400 && statusCode < 500) {
            return "Client Errors";
        } else if (statusCode >= 500 && statusCode < 600) {
            return "Server Errors";
        } else {
            return "Unknown";
        }
    }
}
