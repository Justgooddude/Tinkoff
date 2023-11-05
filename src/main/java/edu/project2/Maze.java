package edu.project2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import static java.lang.System.out;

public class Maze {
    Cell[][] maze = new Cell[1][1];
    int height;
    int width;
    Cell start;
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

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
            curent=curent.setVisit(true);
            maze[curent.xcord()][curent.ycord()]=curent;
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

    public void print() {

        for (int i = 0; i < height; i++) {

            for (int j = 0; j < width; j++) {
                switch (maze[i][j].type()) {
                    case SPACE -> out.print("   ");
                    case WALL -> out.print(" + ");
                    case PATH -> out.print(" . ");
                    default -> out.print("  ");
                }
            }
            out.println("");
        }
    }

    public List<Cell> solve() {
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                maze[i][j]=maze[i][j].setVisit(false);
            }
        }
        Cell curent = start;
        Deque<Cell> stack = new ArrayDeque<>();
        stack.push(start);
        Cell finale = maze[height - 2][width - 2];
        while (stack.size() > 0 && (curent.xcord() != finale.xcord()||curent.ycord()!= finale.ycord())) {
            List<Cell> neighbours = findNeighboursSolve(curent);
            curent=curent.setVisit(true);
            maze[curent.xcord()][curent.ycord()]=curent;
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
        List<Cell> result = stack.stream().toList();
        while (stack.size() > 0) {
            Cell cur = stack.pop();
            maze[cur.xcord()][cur.ycord()] = cur.setType(Cell.Type.PATH);
        }
        maze[finale.xcord()][finale.ycord()]=finale.setType(Cell.Type.PATH);
        return result;
    }

    public void makeProhod(Cell c1, Cell c2) {
        int x = (c1.xcord() + c2.xcord()) / 2;
        int y = (c1.ycord() + c2.ycord()) / 2;
        maze[x][y] = new Cell(x, y, Cell.Type.SPACE, true);
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

    public List<Cell> findNeighboursSolve(Cell c) {
        List<Cell> result = new ArrayList<>();
        if (c.ycord() - 1 > 0) {
            if (!maze[c.xcord()][c.ycord() - 1].visited()&&maze[c.xcord()][c.ycord() - 1].type()!= Cell.Type.WALL) {
                result.add(new Cell(c.xcord(), c.ycord() - 1, Cell.Type.SPACE, true));
            }
        }
        if (c.ycord() + 1 < height) {
            if (!maze[c.xcord()][c.ycord() + 1].visited()&&maze[c.xcord()][c.ycord() + 1].type()!= Cell.Type.WALL) {
                result.add(new Cell(c.xcord(), c.ycord() + 1, Cell.Type.SPACE, true));
            }
        }
        if (c.xcord() - 1 > 0) {
            if (!maze[c.xcord() - 1][c.ycord()].visited()&&maze[c.xcord()-1][c.ycord() ].type()!= Cell.Type.WALL) {
                result.add(new Cell(c.xcord() - 1, c.ycord(), Cell.Type.SPACE, true));
            }
        }
        if (c.xcord() + 1 < width) {
            if (!maze[c.xcord() + 1][c.ycord()].visited()&&maze[c.xcord()+1][c.ycord() ].type()!= Cell.Type.WALL) {
                result.add(new Cell(c.xcord() + 1, c.ycord(), Cell.Type.SPACE, true));
            }
        }
        return result;

    }

}
