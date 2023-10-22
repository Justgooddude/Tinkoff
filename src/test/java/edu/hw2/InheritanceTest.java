package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class InheritanceTest {
    @Test
    void TestOfMethods(){
        Square square= new Square();
        Rectangle rect = new Rectangle();
        rect =square.setWidth(20);
        rect = rect.setHeight(10);
        square.setSide(20);
        Assertions.assertEquals(square.area(),400);
        Assertions.assertEquals(rect.area(),200);
    }
}
