package edu.project3;

public class Main {
    private Main() {

    }

    @SuppressWarnings("RegexpSinglelineJava")//делаю из-за специфичности вывода логера
    public static void main(String[] args) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(args);
        String result = logAnalyzer.fullyAnalyze();
        System.out.println(result);
    }
}
