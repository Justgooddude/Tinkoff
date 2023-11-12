package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class DateParser {
   @SuppressWarnings("MagicNumber")
    Optional<LocalDate> parseDate(String string) {
        if (string.equalsIgnoreCase("today")) {
            return Optional.of(LocalDate.now());
        }
        if (string.equalsIgnoreCase("tommorow")) {
            return Optional.of(LocalDate.now().plusDays(1));
        }
        if (string.equalsIgnoreCase("yesterday")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        if (string.equalsIgnoreCase("1 day ago")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }
        if (Pattern.matches("\\d+ days ago", string)) {
            String days = string.substring(0, string.indexOf(' '));
            int daydif = Integer.valueOf(days);
            return Optional.of(LocalDate.now().minusDays(daydif));
        }
        Pattern pattern1 = Pattern.compile("//d+");
        if (Pattern.matches("\\d+-\\d{1,2}-\\d{1,2}", string)) {
            String[] data = pattern1.split(string);
            if (Integer.valueOf(data[1]) <= 12) {
                LocalDate comp = LocalDate.of(1, Integer.valueOf(data[1]), 1);
                if (Integer.valueOf(data[2]) <= comp.lengthOfMonth()) {
                    return Optional.of(LocalDate.of(
                        Integer.valueOf(data[0]),
                        Integer.valueOf(data[1]),
                        Integer.valueOf(data[2])
                    ));
                }
            }
        }

        if (Pattern.matches("\\d+\\d{1,2}\\d{1,2}", string)) {
            String[] data = pattern1.split(string);
            if (Integer.valueOf(data[1]) <= 12) {
                LocalDate comp = LocalDate.of(1, Integer.valueOf(data[1]), 1);
                if (Integer.valueOf(data[0]) <= comp.lengthOfMonth()) {
                    return Optional.of(LocalDate.of(
                        Integer.valueOf(data[2]),
                        Integer.valueOf(data[1]),
                        Integer.valueOf(data[0])
                    ));
                }
            }
        }
        return Optional.empty();
    }
}
