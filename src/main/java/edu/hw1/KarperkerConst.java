package edu.hw1;

import java.util.Arrays;

public class KarperkerConst {

    public KarperkerConst() {
    }

    @SuppressWarnings("MagicNumber")
    public int countIter(int dig, int iteration) {
        if (dig == 6174) {
            return iteration;
        } else {
            int temp = karperkerFunc(dig);
            return countIter(temp, iteration + 1);
        }
    }

    @SuppressWarnings("MagicNumber")
    public int karperkerFunc(int num) {
        char[] numChar = String.valueOf(num).toCharArray();
        Arrays.sort(numChar);
        //отсортированное по возрастанию число
        String strnum = new String(numChar);

        StringBuilder strn = new StringBuilder(strnum);
        //отсортированное по убыванию
        String reversednumnum = new String(strn.reverse());
        //превращаем их из строк в числа и считаем
        return -Integer.valueOf(strnum) + Integer.valueOf(reversednumnum);
    }

}
