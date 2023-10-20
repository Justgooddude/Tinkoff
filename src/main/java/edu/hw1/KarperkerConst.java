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
        //отсортированное по убыванию
        String reversednumnum = revers(strnum);
        //превращаем их из строк в числа и считаем
        return -Integer.valueOf(strnum) + Integer.valueOf(reversednumnum);
    }

    @SuppressWarnings("MagicNumber")
    private String revers(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[charArray.length - i - 1];
            charArray[charArray.length - i - 1] = temp;
        }
        String reversedStr = new String(charArray);
        return reversedStr;
    }
}
