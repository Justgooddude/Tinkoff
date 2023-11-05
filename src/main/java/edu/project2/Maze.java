package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Maze {
    Cell[][] maze = new Cell[1][1];
    int height;
    int width;
    Cell start;

    public Maze(int heigh, int widt) {
        height = heigh;
        width = widt;
        start = new Cell(1, 1, Cell.Type.PATH, true);
        maze = new Cell[heigh][widt];
    }

    public void generateMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = new Cell(i, j, Cell.Type.SPACE, false);
                } else {
                    maze[i][j] = new Cell(i, j, Cell.Type.WALL, false);
                }
            }
        }
        Cell curent = start;
        Deque<Cell> stack = new ArrayDeque<>();
        stack.push(start);
        while (stack.size() > 0) {
            List<Cell> neighbours = findNeighbours(curent);
            if (neighbours.size() > 0) {
                Random random = new Random();
                int pos = random.nextInt(neighbours.size());
                Cell nextCell = neighbours.get(pos);
                makeProhod(curent, nextCell);
                stack.push(curent);
                curent = nextCell;
            } else {
                curent = stack.pop();
            }
        }
    }

    public void makeProhod(Cell c1, Cell c2) {
        int x = (c1.xcord() + c2.xcord()) / 2;
        int y = (c1.ycord() + c2.ycord()) / 2;
        maze[x][y] = new Cell(x, y, Cell.Type.SPACE, true);
        maze[c2.xcord()][c2.ycord()] = new Cell(c2.xcord(), c2.ycord(), Cell.Type.SPACE, true);
    }

    public List<Cell> findNeighbours(Cell c) {
        List<Cell> result = new ArrayList<>();
        if (c.ycord() - 2 > 0) {
            if (!maze[c.xcord()][c.ycord() - 2].visited()) {
                result.add(new Cell(c.xcord(), c.ycord() - 2, Cell.Type.SPACE, true));
            }
        }
        if (c.ycord() + 2 < height) {
            if (!maze[c.xcord()][c.ycord() + 2].visited()) {
                result.add(new Cell(c.xcord(), c.ycord() + 2, Cell.Type.SPACE, true));
            }
        }
        if (c.xcord() - 2 > 0) {
            if (!maze[c.xcord() - 2][c.ycord()].visited()) {
                result.add(new Cell(c.xcord() - 2, c.ycord(), Cell.Type.SPACE, true));
            }
        }
        if (c.xcord() + 2 < width) {
            if (!maze[c.xcord() + 2][c.ycord()].visited()) {
                result.add(new Cell(c.xcord() + 2, c.ycord(), Cell.Type.SPACE, true));
            }
        }
        return result;

    }

}
