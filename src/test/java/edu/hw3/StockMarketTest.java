package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockMarketTest {

    @Test
    void mostValuableStock() {
        StockMarket stockMarket=new StockMarket();
        stockMarket.add(new Stock(12));
        stockMarket.add(new Stock(16));
        stockMarket.add(new Stock(24));
        stockMarket.add(new Stock(18));
        Stock test = new Stock(24);
        Stock result = stockMarket.mostValuableStock();
        Assertions.assertEquals(result.value,test.value);
    }
}
