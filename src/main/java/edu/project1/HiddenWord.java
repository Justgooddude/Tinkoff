package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class HiddenWord {
    private static String[] dictionary = new String[]{
        "hello","world","commit",
        "push","class","interface",
        "array","integer","good","evening"
    }
        ;
    private String HidenWord;
    private String WordInStars;
    Random randomWord = new Random();
    Scanner scannerForLetter = new Scanner(System.in);

    public HiddenWord(){
        this.HidenWord="";
        this.WordInStars="";
    }

    public void openlatter(char letter){
        for (int i=0;i < HidenWord.length();i++){
            if(HidenWord.toCharArray()[i]==letter){
                WordInStars = WordInStars.substring(0, i) + letter + WordInStars.substring( i + 1);
            }
        }
    }
    public void hideWord(){
        for (int i=0;i < HidenWord.length();i++){
            WordInStars+="*";
        }
    }

    public void getRandomWorld(){
        int randomnumber= randomWord.nextInt(dictionary.length);
        this.HidenWord=dictionary[randomnumber];
    }

    public String getlatter(){
        return scannerForLetter.next();
    }
    public boolean checklater(char letter){
        if(HidenWord.indexOf(letter)>=0){
            return true;
        }
        else{
            return false;
        }
    }
    public void printHiddenWorld(){
        System.out.println(WordInStars);
    }
    public boolean victory(){
        if (WordInStars.indexOf('*')==-1){
            return true;
        }
        return false;
    }


}
