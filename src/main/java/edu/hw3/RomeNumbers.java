package edu.hw3;

public class RomeNumbers {
   private int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private String[] romanvalues = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public RomeNumbers(){}
    public String convertToRoman(int number){
        String result="";
        for(int i=0;i<values.length;i++)
        {
            while(number >= values[i])
            {
                number = number - values[i];
                result+=romanvalues[i];
            }
        }
        return result;
    }
}
