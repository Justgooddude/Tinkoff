package edu.project2;


public record Cell(int xcord, int ycord, Type type, boolean visited) {
    enum Type {
        WALL, SPACE, PATH;
    }
}
