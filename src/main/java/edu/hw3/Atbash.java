package edu.hw3;


public class Atbash {
    public Atbash() {

    }

    public String atbash(String origin) {
        char[] originCode = origin.toCharArray();
        char[] codedOrigin = new char[originCode.length];
        for (int i = 0; i < originCode.length; i++) {
            codedOrigin[i] = originCode[i];
            if (originCode[i] >= 'a' && originCode[i] <= 'z') {
                int coded = 'z' - originCode[i] + 'a';
                codedOrigin[i] = (char) coded;
            }
            if (originCode[i] >= 'A' && originCode[i] <= 'Z') {
                int coded = 'Z' - originCode[i] + 'A';
                codedOrigin[i] = (char) coded;
            }
        }
        return new String(codedOrigin);
    }

}
