package edu.project2;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static edu.project2.Cell.Type.PATH;
import static edu.project2.Cell.Type.SPACE;
import static edu.project2.Cell.Type.WALL;
import static java.lang.Integer.max;

public class DisplayGraphics extends Frame {
    Maze maze = new Maze(1, 1);
    int size;

    public DisplayGraphics(Maze maz, int borders) {
        maze = maz;
        size = borders / max(maze.height, maze.width);
        setVisible(true);
        setSize(borders, borders);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        paint(getGraphics());
    }

    @SuppressWarnings("MagicNumber")
    public void paint(Graphics g) {
        for (int i = 0; i < maze.height; i++) {
            for (int j = 0; j < maze.width; j++) {
                switch (maze.maze[i][j].type()) {
                    case WALL -> setForeground(Color.BLACK);
                    case PATH -> setForeground(Color.red);
                    case SPACE -> setForeground(Color.WHITE);
                    default -> setForeground(Color.black);
                }
                g.fillRect(maze.maze[i][j].xcord() * 40, maze.maze[i][j].ycord() * 40, 40, 40);
            }
        }
        setForeground(Color.BLACK);
        g.fillRect(20, 20, 100, 100);
    }

}
