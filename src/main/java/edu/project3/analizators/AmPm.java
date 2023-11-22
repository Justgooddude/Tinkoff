package edu.project3.analizators;

import edu.project3.NginxBody;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.project3.Report.create1x3row;

public class AmPm {
    private AmPm() {
    }

    @SuppressWarnings("MagicNumber")
    public static String analyze(List<NginxBody> logs) {
        Map<String, Long> statusCounts = logs.stream()
            .collect(Collectors.groupingBy(
                AmPm::getCategory,
                Collectors.counting()
            ));

        Locale locale = Locale.forLanguageTag("ru-RU");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat df = new DecimalFormat("#.##", symbols);

        String res = "";
        for (Map.Entry<String, Long> entry : statusCounts.entrySet()) {
            String timeOfDay = entry.getKey();
            long count = entry.getValue();
            double percent = (count * 100.0) / logs.size();

            res += create1x3row(timeOfDay, Long.toString(count), df.format(percent));
        }

        return res;
    }

    @SuppressWarnings("MagicNumber")
    private static String getCategory(NginxBody nginxLogItem) {
        int hour = nginxLogItem.timeLocal().getHour();
        if (hour < 12) {
            return "Am";
        }
        return "Pm";
    }
}
