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
                        if ((xCoord >= 0) && (xCoord < 8) && (yCoord >= 0) && (yCoord < 8)) {
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
}
