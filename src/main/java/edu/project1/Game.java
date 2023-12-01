package edu.project1;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MultipleStringLiterals")
public class Game {
    private boolean endofGame = false;
    private final static int MAX_MISTAKES = 6;

    private final static String GREETINGS = "Добро пожаловать.  Для выхода используется EXIT";
    private int currentMistake = 0;
    private static String endWorld = "EXIT";

    List<Character> guesedCorrectLatters = new ArrayList<>();
    List<Character> guesedMistakeLatters = new ArrayList<>();
    HiddenWord hiddenWord = new HiddenWord();

    private boolean checkrepeatLater(String input) {
        if (input.length() > 1) {
            return true;
        }
        if (guesedMistakeLatters.indexOf(input) >= 0) {
            return true;
        }
        if (guesedMistakeLatters.indexOf(input) >= 0) {
            return true;
        }
        return false;
    }

    private boolean lose() {
        return currentMistake > MAX_MISTAKES;
    }

    //Не использую логгер тюк выводится много лошнней информации и из-за этого портится вывод
    @SuppressWarnings("RegexpSinglelineJava")
    public boolean gamerun() {
        hiddenWord.getRandomWorld();
        hiddenWord.hideWord();
        System.out.println(GREETINGS);
        while (!endofGame) {
            hiddenWord.printHiddenWorld();
            System.out.println("Введите букву");
            String input = hiddenWord.getlatter();
            while (checkrepeatLater(input)) {
                if (input.equalsIgnoreCase(endWorld)) {
                    endofGame = true;
                    break;
                }
                input = hiddenWord.getlatter();
            }
            if (hiddenWord.checklater(input.toCharArray()[0])) {
                guesedCorrectLatters.add(input.toCharArray()[0]);
                hiddenWord.openlatter(input.toCharArray()[0]);
            } else if (!guesedMistakeLatters.contains(input.charAt(0))) {
                guesedMistakeLatters.add(input.toCharArray()[0]);
                currentMistake++;
                System.out.println("Количество ошибок " + currentMistake + " Из " + MAX_MISTAKES);
            }
            if (lose()) {
                endofGame = true;
                System.out.println("You lose");
                return false;
            }
            if (hiddenWord.victory()) {
                endofGame = true;
                System.out.println("Congratulations");
                return true;
            }
        }
        return true;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public boolean gamerun(String hidenWord, char[] letters) {
        hiddenWord.setHidenWord(hidenWord);
        hiddenWord.hideWord();
        int curLet = 0;
        System.out.println(GREETINGS);
        while (!endofGame) {
            hiddenWord.printHiddenWorld();
            System.out.println("Введите букву");
            String input = String.valueOf(letters[curLet]);
            while (!checkrepeatLater(input)) {
                if (input.equalsIgnoreCase(endWorld)) {
                    endofGame = true;
                    break;
                }
                input = hiddenWord.getlatter();
            }
            if (hiddenWord.checklater(input.charAt(0))) {
                guesedCorrectLatters.add(input.charAt(0));
                hiddenWord.openlatter(input.charAt(0));
            } else if(!guesedCorrectLatters.contains(input.charAt(0))){
                guesedMistakeLatters.add(input.charAt(0));
                currentMistake++;
                System.out.println("Количество ошибок " + currentMistake + " Из " + MAX_MISTAKES);
            }
            if (lose()) {
                endofGame = true;
                System.out.println("You lose");
                return false;
            }
            if (hiddenWord.victory()) {
                endofGame = true;
                System.out.println("Congratulations");
                return true;
            }
            curLet += 1;
        }
        return true;
    }
}
