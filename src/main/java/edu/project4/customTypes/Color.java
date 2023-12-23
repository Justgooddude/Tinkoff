package edu.project4.customTypes;

import java.util.Random;

public record Color(int r, int g, int b) {
    private final static int RGB_MAX = 256;

    public static Color generate() {
        Random random = new Random();
        int r = random.nextInt(0, RGB_MAX);
        int g = random.nextInt(0, RGB_MAX);
        int b = random.nextInt(0, RGB_MAX);
        return new Color(r, g, b);
    }
}
