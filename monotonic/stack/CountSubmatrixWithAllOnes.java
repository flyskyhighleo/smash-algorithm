package monotonic.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/count-submatrices-with-all-ones/description/
Given an m x n binary matrix mat, return the number of submatrices that have all ones.
 */
public class CountSubmatrixWithAllOnes {
    public int countMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] compressed = compress(mat);

        int count = 0;

        for (int i = 0; i < m; i++) {
            count += countAllOneMats(compressed[i]);
        }

        return count;
    }

    private int[][] compress(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] compressed = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int prev = i == 0 ? 0 : compressed[i - 1][j];
                compressed[i][j] = mat[i][j] == 0 ? 0 : prev + 1;
            }
        }

        return compressed;
    }

    private int countAllOneMats(int[] heights) {
        int n = heights.length;
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            while (!stack.isEmpty() && height <= heights[stack.peek()]) {
                int cur = stack.pop();
                int curHeight = heights[cur];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int largerHeight = Math.max(heights[left], height);
                while (curHeight > largerHeight) {
                    count += (i - left) * (i - left - 1) / 2;
                    curHeight--;
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int curHeight = heights[cur];
            int left = stack.isEmpty() ? -1 : stack.peek();
            int largerHeight = left == -1 ? 0 : heights[left];
            while (curHeight > largerHeight) {
                count += (n - left) * (n - left - 1) / 2;
                curHeight--;
            }
        }

        return count;
    }
}
