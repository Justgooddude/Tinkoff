package edu.hw1;

public class Problems2_6 {
    private Problems2_6(){
    }

    public int countdigits(int numb){
        int count=1;
        for(int i = numb;i>0; i = i /numb ){
            count++;
        }
        return count;
    }

    public boolean inNestable(int[] mas1, int[] mas2){
        int min1=mas1[0],min2=mas2[0],max1=mas1[0],max2=mas2[0];
        for (int elem : mas1){
            if(elem<min1){
                min1=elem;
            }
            if(elem>max1){
                max1=elem;
            }
        }
        for (int elem : mas2){
            if(elem<min2){
                min2=elem;
            }
            if(elem>max2){
                max2=elem;
            }
        }
        if((min1>min2) & (max1<max2)){
            return true;
        } else {
            return false;
        }
    }



}
