package edu.hw3;

import java.util.ArrayList;

public class Braces {
    public Braces(){}
    public ArrayList<String> clusterize(String origin){
        char[] originArray=origin.toCharArray();
        int openedBraces=0;
        int clusterbeginindex=0;
        ArrayList<String>result= new ArrayList<>();
        for (int i=0;i<originArray.length;i++){
           if(originArray[i]==')'){
               openedBraces--;
               if (openedBraces==0){
                 String clust = origin.substring(clusterbeginindex,i+1);
                 result.add(clust);
               }
           }
           if (originArray[i]=='('){
               if(openedBraces==0){
                   clusterbeginindex=i;
               }
               openedBraces++;
           }

        }
        return result;
    }
}
