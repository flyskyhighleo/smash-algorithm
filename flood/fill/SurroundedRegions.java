package flood.fill;

/*
https://leetcode.com/problems/surrounded-regions/description/
Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
public class SurroundedRegions {

    private static final char BOARDER = 'B';
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public void surroundedRegions(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // fill 1st and the last row with BOARDER
        for (int c = 0; c < n; c++) {
            if (board[0][c] == 'O') {
                fill(board, 0, c, 'O', BOARDER);
            }

            if (board[m - 1][c] =='O') {
                fill(board, m - 1, c, 'O', BOARDER);
            }
        }

        // fill 1st and last col with BOARDER
        for (int r = 0; r < m; r++) {
            if (board[r][0] == 'O') {
                fill(board, r, 0, 'O', BOARDER);
            }

            if (board[r][n - 1] == 'O') {
                fill(board, r, n - 1, 'O', BOARDER);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void fill(char[][] board, int x, int y, char origin, char color) {
        int m = board.length;
        int n = board[0].length;

        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }

        if (board[x][y] != origin) {
            return;
        }

        board[x][y] = color;

        for (int[] dir : DIRECTIONS) {
            int nextRow = x + dir[0];
            int nextCol = y + dir[1];
            fill(board, nextRow, nextCol, origin, color);
        }
    }
}
