package edu.hw3;

public class Stock implements Comparable<Stock> {
    public String name;
    public int value;
    public Stock(){
        this.name="Tesla";
        this.value=199;
    }
    public Stock(int value){
        this.name="Tesla";
        this.value=value;
    }
    public int compareTo( Stock s){
        return value-s.value;
    }

}
