package flood.fill;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/bricks-falling-when-hit/description/
You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space. A brick is stable if:

1. It is directly connected to the top of the grid, or
2. At least one other brick in its four adjacent cells is stable.

You are also given an array hits, which is a sequence of erasures we want to apply. Each time we want to erase the brick
at the location hits[i] = (rowi, coli). The brick on that location (if it exists) will disappear. Some other bricks may
no longer be stable because of that erasure and will fall. Once a brick falls, it is immediately erased from the grid
(i.e., it does not land on other stable bricks).

Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.

Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.
 */
public class BricksFallingWhenHit {

    private static final int EMPTY = 0;
    private static final int BREAK = -1;
    private static final int DROP = -2;
    private static final int BRICK = 1;
    private static final int STABLE = 2;
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[hits.length];

        for (int[] hit : hits) {
            int x = hit[0];
            int y = hit[1];
            if (grid[x][y] != 0) {
                grid[x][y] = -1;
            }
        }

        // mark stables
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1 && r == 0) {
                    Set<Integer> blockers = new HashSet<>(Arrays.asList(EMPTY, BREAK, STABLE));
                    dfs(grid, r, c, blockers, 2);
                }
            }
        }

        for (int i = hits.length - 1; i >= 0; i--) {
            int x = hits[i][0];
            int y = hits[i][1];
            if (grid[x][y] == -1) {
                grid[x][y] = 1;
                boolean canFill = x == 0;
                for (int[] dir : DIRECTIONS) {
                    int nextRow = x + dir[0];
                    int nextCol = y + dir[1];
                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                        continue;
                    }
                    if (grid[nextRow][nextCol] == STABLE || grid[nextRow][nextCol] == DROP) {
                        canFill = true;
                        break;
                    }
                }
                if (canFill) {
                    Set<Integer> blockers = new HashSet<>(Arrays.asList(EMPTY, STABLE, BREAK, DROP));
                    res[i] = dfs(grid, x, y, blockers, DROP) - 1;
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int r, int c, Set<Integer> blockers, int color) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        if (blockers.contains(grid[r][c])) {
            return 0;
        }

        grid[r][c] = color;

        int area = 0;
        for (int[] dir : DIRECTIONS) {
            int nextRow = r + dir[0];
            int nextCol = c + dir[1];
            area += dfs(grid, nextRow, nextCol, blockers, color);
        }

        return area + 1;
    }
}
