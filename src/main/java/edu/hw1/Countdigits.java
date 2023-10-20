package edu.hw1;

public class Countdigits {

    public Countdigits() {
    }

    @SuppressWarnings("MagicNumber") public int countdigits(int numb) {
        int count = 1;
        for (int i = numb / 10; i > 0; i = i / 10) {
            count++;
        }
        return count;
    }
}
