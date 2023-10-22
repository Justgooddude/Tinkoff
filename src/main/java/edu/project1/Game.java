package edu.project1;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean endofGame = false;
    private int maxMistakes=6;
    private int currentMistake=0;
    private static String endWorld = "EXIT";

    List<Character> guesedCorrectLatters=new ArrayList<>();
    List<Character> guesedMistakeLatters=new ArrayList<>();
    HiddenWord hiddenWord= new HiddenWord();

    private boolean checkrepeatLater(String input){
        if (input.length()>1){
            return true;
        } else if (guesedMistakeLatters.indexOf(input)>=0) {
            return true;
        }else if (guesedMistakeLatters.indexOf(input)>=0){
            return true;
        }else {
            return false;
        }
    }
    private boolean lose(){
        return currentMistake>maxMistakes;
    }

    public boolean Gamerun(){
        hiddenWord.getRandomWorld();
        hiddenWord.hideWord();
        System.out.println("Добро пожаловать.  Для выхода используется EXIT" );
        while (!endofGame){
            hiddenWord.printHiddenWorld();
            System.out.println("Введите букву");
            String input = hiddenWord.getlatter();
            while (checkrepeatLater(input)){
                if (input=="EXIT"){
                    endofGame=true;
                    break;
                }
                input= hiddenWord.getlatter();
            }
            if(hiddenWord.checklater(input.toCharArray()[0])){
                guesedCorrectLatters.add(input.toCharArray()[0]);
                hiddenWord.openlatter(input.toCharArray()[0]);
            }else {
                guesedMistakeLatters.add(input.toCharArray()[0]);
                currentMistake++;
                System.out.println("Количество ошибок "+currentMistake+" Из "+ maxMistakes);
            }
            if(lose()){
                endofGame=true;
                System.out.println("You lose");
                return false;
            }
            if(hiddenWord.victory()){
                endofGame=true;
                System.out.println("Congratulations");
                return true;
            }
        }
        return true;
    }

}
