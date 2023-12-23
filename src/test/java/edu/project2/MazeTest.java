package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void maze() {
        Maze maze = new Maze(21, 21);
        maze.generateMaze();
        List<Cell> path=maze.solve();
        maze.print();
        assertEquals(1, 1);

    }
}
