package edu.hw1;

public class Nestable {

    public Nestable() {
    }

    @SuppressWarnings("MagicNumber") public boolean inNestable(int[] mas1, int[] mas2) {
        int min1 = mas1[0];
        int min2 = mas2[0];
        int max1 = mas1[0];
        int max2 = mas2[0];
        //стандартные поиски минимума и макс в массивах
        for (int elem : mas1) {
            if (elem < min1) {
                min1 = elem;
            }
            if (elem > max1) {
                max1 = elem;
            }
        }
        for (int elem : mas2) {
            if (elem < min2) {
                min2 = elem;
            }
            if (elem > max2) {
                max2 = elem;
            }
        }
        //проверка условий вложенности
        if ((min1 > min2) & (max1 < max2)) {
            return true;
        }
        return false;

    }
}
