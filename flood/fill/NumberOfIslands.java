package flood.fill;

public class NumberOfIslands {

    private static int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int numIslands(char[][] grids) {
        int count = 0;
        int m = grids.length;
        int n = grids[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grids[i][j] == '1') {
                    count++;
                    floodFill(grids, i, j, '0');
                }
            }
        }

        return count;
    }

    private void floodFill(char[][] grids, int r, int c, char flood) {
        int m = grids.length;
        int n = grids[0].length;

        if (r < 0 || r >= m || c < 0 || c >= n) {
            return;
        }

        if (grids[r][c] == flood) {
            return;
        }

        grids[r][c] = flood;

        for (int[] dir : directions) {
            int nextRow = r + dir[0];
            int nextCol = c + dir[1];
            floodFill(grids, nextRow, nextCol, flood);
        }
    }
}
