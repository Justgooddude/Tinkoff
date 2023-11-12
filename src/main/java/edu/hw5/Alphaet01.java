package edu.hw5;

import java.util.regex.Pattern;

public class Alphaet01 {
    public boolean third0(String string) {
        return Pattern.matches("[01]{2}0[01]*", string);
    }

    public boolean starteqEnd(String string) {
        return Pattern.matches("0[01]*0", string)
            || Pattern.matches("1[01]*1", string);
    }

    public boolean length(String string) {
        return Pattern.matches("[01]{1,3}", string);
    }
}
