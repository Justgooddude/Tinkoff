package edu.hw1;

public class Chess {

    public Chess() {
    }

    @SuppressWarnings("MagicNumber") public boolean knightBoardCapture(int[][] board) {
        int[][] knightMoves = new int[][] {
            {1, 2}, {1, -2},
            {-1, 2}, {-1, -2},
            {2, 1}, {2, -1},
            {-2, 1}, {-2, -1}
        };
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : knightMoves) {
                        int xCoord = i + move[0];
                        int yCoord = j + move[1];
                        //проверки что остались в пределах доски, и наличие коня
                        if (onBoard(xCoord, yCoord)) {
                            if (board[xCoord][yCoord] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean onBoard(int x, int y) {
        final int BOARDSIZE = 8;
        return (x >= 0) && (x < BOARDSIZE) && (y >= 0) && (y < BOARDSIZE);
    }

}
