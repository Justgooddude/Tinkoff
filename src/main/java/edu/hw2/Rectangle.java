package edu.hw2;

public class Rectangle {
    protected int width;
    protected int height;

    Rectangle setWidth(int width) {
        Rectangle result = new Rectangle();
        result.width=width;
        result.height=this.height;
        return result;
    }

    Rectangle setHeight(int height) {
        Rectangle result = new Rectangle();
        result.width=this.width;
        result.height=height;
        return result;
    }

    public int getWidth(){
        return this.width;
    }
    public int getheight(){
        return this.height;
    }

    double area() {
        return width * height;
    }
}
