package monotonic.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/maximal-rectangle/description/
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
========================================================================================================================

 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        int[][] heights = compress(matrix);

        for (int[] height : heights) {
            max = Math.max(max, maxRectangle(height));
        }

        return max;
    }

    // find max rectangle based on a row
    private int maxRectangle(int[] height) {
        int max = 0;
        int n = height.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int area = height[cur] * (i - left - 1);
                max = Math.max(area, max);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int area = height[cur] * (n - left - 1);
            max = Math.max(area, max);
        }

        return max;
    }

    private int[][] compress(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] heights = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int prev = i == 0 ? 0 : heights[i - 1][j];
                heights[i][j] = matrix[i][j] == '0' ? 0 : 1 + prev;
            }
        }

        return heights;
    }
}
