package edu.project3.analizators;

import edu.project3.NginxBody;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import static edu.project3.Report.create1x2row;

public class AverageSreverReport {
    private AverageSreverReport() {
    }

    public static String analyze(List<NginxBody> nginxLogItems) {
        double averageResponse = nginxLogItems.stream()
            .mapToLong(NginxBody::bodyBytesSent)
            .average()
            .orElse(0.0);

        Locale locale = Locale.forLanguageTag("ru-RU");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        String formattedValue = df.format(averageResponse);

        return create1x2row("Average server response", formattedValue);
    }
}
