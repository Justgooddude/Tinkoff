package edu.project2;

public record Cell(int xcord, int ycord, Type type, boolean visited) {
    enum Type {
        WALL, SPACE, PATH;
    }

    public Cell setType(Type typ) {
        return new Cell(xcord, ycord, typ, visited);
    }

    public Cell setVisit(boolean visit) {
        return new Cell(xcord, ycord, type, visit);
    }
}
