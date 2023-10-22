package edu.project1;

import java.util.Random;
import java.util.Scanner;

public class HiddenWord {
    private static String[] dictionary = new String[] {
        "hello", "world", "commit",
        "push", "class", "interface",
        "array", "integer", "good", "evening"
    };
    private String hidenWord;
    private String wordInStars;
    Random randomWord = new Random();
    Scanner scannerForLetter = new Scanner(System.in);

    public HiddenWord() {
        this.hidenWord = "";
        this.wordInStars = "";
    }

    public void openlatter(char letter) {
        for (int i = 0; i < hidenWord.length(); i++) {
            if (hidenWord.toCharArray()[i] == letter) {
                wordInStars = wordInStars.substring(0, i) + letter + wordInStars.substring(i + 1);
            }
        }
    }

    public void hideWord() {
        for (int i = 0; i < hidenWord.length(); i++) {
            wordInStars += "*";
        }
    }

    public void getRandomWorld() {
        int randomnumber = randomWord.nextInt(dictionary.length);
        this.hidenWord = dictionary[randomnumber];
    }

    public String getlatter() {
        return scannerForLetter.next();
    }

    public boolean checklater(char letter) {
        if (hidenWord.indexOf(letter) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void printHiddenWorld() {
        System.out.println(wordInStars);
    }

    public boolean victory() {
        if (wordInStars.indexOf('*') == -1) {
            return true;
        }
        return false;
    }

}
