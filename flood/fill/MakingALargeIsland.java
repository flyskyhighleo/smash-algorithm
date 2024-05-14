package flood.fill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/making-a-large-island/description/
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.
 */
public class MakingALargeIsland {

    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int largestIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int res = 1;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 0) {
//                    int color = convert(i, j, m, n);
//                    grid[i][j] = color;
//                    int area = dfs(grid, i, j, color);
//                    res = Math.max(res, area);
//                    grid[i][j] = 0;
//                }
//            }
//        }
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (grid[i][j] == 1) {
//                    int area = dfs(grid, i, j, 0);
//                    res = Math.max(res, area);
//                }
//            }
//        }

        int index = 2;
        Map<Integer, Integer> indexToArea = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, index);
                    indexToArea.put(index, area);
                    index++;
                    res = Math.max(res, area);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int connectedArea = 1;
                    Set<Integer> visited = new HashSet<>();
                    for (int[] dir : DIRECTIONS) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            continue;
                        }
                        if (grid[x][y] != 0 && !visited.contains(grid[x][y])) {
                            visited.add(grid[x][y]);
                            connectedArea += indexToArea.get(grid[x][y]);
                        }
                    }
                    res = Math.max(res, connectedArea);
                }
            }
        }

        return res;
    }

    private int convert(int x, int y, int m, int n) {
        return x * n + y + 2;
    }

    private int dfs(int[][] grid, int r, int c, int color) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m || c < 0 || c >= n) {
            return 0;
        }

        if (grid[r][c] == 0 || grid[r][c] == color) {
            return 0;
        }

        grid[r][c] = color;

        int area = 0;
        for (int[] dir : DIRECTIONS) {
            int nextRow = r + dir[0];
            int nextCol = c + dir[1];
            area += dfs(grid, nextRow, nextCol, color);
        }

        return area + 1;
    }
}
