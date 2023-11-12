package edu.hw5;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {
    DateParser date = new DateParser();
    @Test
    void parseDate() {
        String enter="11 days ago";
        assertEquals(date.parseDate(enter), Optional.of(LocalDate.now().minusDays(11)));
    }
}
