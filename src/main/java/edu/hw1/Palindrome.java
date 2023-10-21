package edu.hw1;

public class Palindrome {

    public Palindrome() {
    }

    @SuppressWarnings("MagicNumber") public boolean isPalindromeDescendant(int num) {
        String strVersionOfNum = String.valueOf(num);
        if (strVersionOfNum.equalsIgnoreCase(revers(strVersionOfNum))) {
            return true;
        }
        //пока число не однозначное начинаем искать потомков
        while (strVersionOfNum.length() > 1) {
            if (strVersionOfNum.equalsIgnoreCase(revers(strVersionOfNum))) {
                return true;
            }
            String old = strVersionOfNum;
            strVersionOfNum = "";
            for (int i = 0; i < (old.length() / 2); i++) {
                //складываем значение символов на местах 2i и 2i+1
                int sum =
                    Character.getNumericValue(old.charAt(2 * i)) + Character.getNumericValue(old.charAt(2 * i + 1));
                strVersionOfNum = strVersionOfNum + sum;
            }
            //проверка на четность кол-ва цифр,сли нечетное,о добавляем упущенный последний символ
            if ((old.length() % 2) == 1) {
                strVersionOfNum = strVersionOfNum + old.charAt(old.length() - 1);
            }
        }
        return false;
    }
    //обычный разорот строки

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
