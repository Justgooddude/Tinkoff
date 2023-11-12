package edu.hw5;

import java.util.regex.Pattern;

public class Paswordcheck {
    public boolean check(String password) {
        return Pattern.matches(".*[~!@#$%^&*|]+.*", password);
    }
}
