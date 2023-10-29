package edu.hw3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StockMarket {
    private PriorityQueue<Stock> market=new PriorityQueue<>(valueComparator);
    public StockMarket(){
        this.market=new PriorityQueue<Stock>(valueComparator);

    }
    public static Comparator<Stock> valueComparator = new Comparator<Stock>(){

        @Override
        public int compare(Stock c1, Stock c2) {
            return (int) (c2.value - c1.value);
        }
    };
    void add(Stock stock){
        market.add(stock);
    }
    void remove(Stock stock){
        market.remove(stock);
    }
    Stock mostValuableStock(){
        return market.poll();
    }
}
