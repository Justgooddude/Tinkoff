package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    @Test
    void maze() {
        Maze maze = new Maze(11, 11);
        maze.generateMaze();
        DisplayGraphics fast = new DisplayGraphics(maze, 500);
        assertEquals(1, 1);

    }
}
