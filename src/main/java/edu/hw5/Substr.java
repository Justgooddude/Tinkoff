package edu.hw5;

import java.util.regex.Pattern;

public class Substr {
    public boolean valid(String s, String t) {
        Pattern ch = Pattern.compile("//w");
        char[] signs = s.toCharArray();
        String reg = ".*";
        for (char chr : signs) {
            reg = reg + chr + ".*";
        }
        return Pattern.matches(reg, t);
    }
}
