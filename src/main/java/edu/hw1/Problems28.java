package edu.hw1;

import java.util.Arrays;

public class Problems28 {

    public Problems28() {
    }

    @SuppressWarnings("MagicNumber") public int countdigits(int numb) {
        int count = 1;
        for (int i = numb / 10; i > 0; i = i / 10) {
            count++;
        }
        return count;
    }

    @SuppressWarnings("MagicNumber") public boolean inNestable(int[] mas1, int[] mas2) {
        int min1 = mas1[0];
        int min2 = mas2[0];
        int max1 = mas1[0];
        int max2 = mas2[0];
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
        if ((min1 > min2) & (max1 < max2)) {
            return true;
        }
        return false;

    }

    @SuppressWarnings("MagicNumber") public String fixString(String broked) {
        char[] old = broked.toCharArray();
        char[] next = new char[old.length];
        System.arraycopy(old, 0, next, 0, old.length);
        for (int i = 0; i < (broked.length() / 2); i++) {
            next[2 * i] = old[2 * i + 1];
            next[2 * i + 1] = old[2 * i];
        }
        String result = new String(next);
        return result;
    }

    @SuppressWarnings("MagicNumber") public boolean isPalindromeDescendant(int num) {
        String str = String.valueOf(num);
        if (str.equalsIgnoreCase(revers(str))) {
            return true;
        }
        while (str.length() > 1) {
            if (str.equalsIgnoreCase(revers(str))) {
                return true;
            }
            String old = str;
            str = "";
            for (int i = 0; i < (old.length() / 2); i++) {
                int sum =
                    Character.getNumericValue(old.charAt(2 * i)) + Character.getNumericValue(old.charAt(2 * i + 1));
                str = str + sum;
            }
            if ((old.length() % 2) == 1) {
                str = str + old.charAt(old.length() - 1);
            }
        }
        return false;
    }

    @SuppressWarnings("MagicNumber") public int countK(int dig) {
        int count = 0;
        int temp = dig;
        while (temp != 6174) {
           temp = k(temp);
           count++;
        }
        return count;
    }

    @SuppressWarnings("MagicNumber") int rotateLeft(int n, int shift) {
        String binary = Integer.toBinaryString(n);
        char[] oldBin = binary.toCharArray();
        char[] newBin = new char[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            int index = (i + shift) % binary.length();
            if (index < 0){
                index+=binary.length();
            }
            newBin[i] = oldBin[index];
        }
        String result = new String(newBin);
        return Integer.parseInt(result, 2);
    }

    @SuppressWarnings("MagicNumber") int rotateRight(int n, int shift) {
        String binary = Integer.toBinaryString(n);
        char[] oldBin = binary.toCharArray();
        char[] newBin = new char[binary.length()];
        for (int i = 0; i < binary.length(); i++) {
            int index = (i - shift) % binary.length();
            if (index < 0){
                index+=binary.length();
            }
            newBin[i] = oldBin[index];
        }
        String result = new String(newBin);
        return Integer.parseInt(result, 2);
    }

    @SuppressWarnings("MagicNumber") public boolean knightBoardCapture(int[][] board) {
        int[][] knightMoves = new int[][] {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : knightMoves) {
                        int xCoord = i + move[0];
                        int yCoord = j + move[1];
                        if ((xCoord >= 0) && (xCoord < 8) && (yCoord >= 0) && (yCoord < 8)) {
                            if (board[xCoord][yCoord] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @SuppressWarnings("MagicNumber") private String revers(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            char temp = charArray[i];
            charArray[i] = charArray[charArray.length - i - 1];
            charArray[charArray.length - i - 1] = temp;
        }
        String reversedStr = new String(charArray);
        return reversedStr;
    }

    @SuppressWarnings("MagicNumber") public int k(int num) {
        char[] numChar = String.valueOf(num).toCharArray();
        Arrays.sort(numChar);
        String strnum = new String(numChar);
        String reversednumnum = revers(strnum);
        return -Integer.valueOf(strnum) + Integer.valueOf(reversednumnum);
    }

}
