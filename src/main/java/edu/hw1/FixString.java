package edu.hw1;

public class FixString {

    public FixString() {
    }

    @SuppressWarnings("MagicNumber") public String fixString(String broked) {
        char[] currentString = broked.toCharArray();
        char[] next = new char[currentString.length];
        //используем copy, чтоб строки не менялись одновременно
        System.arraycopy(currentString, 0, next, 0, currentString.length);
        for (int i = 0; i < (broked.length() / 2); i++) {
            //переставлем соседние элементы
            next[2 * i] = currentString[2 * i + 1];
            next[2 * i + 1] = currentString[2 * i];
        }
        String result = new String(next);
        return result;
    }
}
