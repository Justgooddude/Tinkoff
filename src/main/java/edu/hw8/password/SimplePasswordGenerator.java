package edu.hw8.password;

import java.util.NoSuchElementException;

public class SimplePasswordGenerator {
    char[] allPosibleChars;
    int[] positionCharIndex;
    long totalAmmountOfPasswords;
    public SimplePasswordGenerator(char[]allPosibleChars,int passwordLength){
        this.allPosibleChars=allPosibleChars;
        this.positionCharIndex=new int[passwordLength];
        this.totalAmmountOfPasswords=(long) Math.pow(allPosibleChars.length,passwordLength);
    }
    public String generatePassword(){
        if (totalAmmountOfPasswords==0){
            throw new NoSuchElementException("");
        }
        StringBuilder pas = new StringBuilder();
        for(int index:positionCharIndex){
            pas.append(allPosibleChars[index]);
        }
        generateNextPassword();
        return new String(pas);
    }
    private void generateNextPassword() {
        totalAmmountOfPasswords--;

        for (int i = positionCharIndex.length - 1; i >= 0; --i) {
            positionCharIndex[i]++;

            if (positionCharIndex[i] == allPosibleChars.length) {
                positionCharIndex[i] = 0;
            } else {
                break;
            }
        }
    }

}
