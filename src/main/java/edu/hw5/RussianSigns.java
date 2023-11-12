package edu.hw5;

import java.util.regex.Pattern;

public class RussianSigns {
    public boolean valid(String sign) {
        return Pattern.matches("[А-Я]\\d{3}[А-Я]{2}\\d{3}", sign);
    }
}
