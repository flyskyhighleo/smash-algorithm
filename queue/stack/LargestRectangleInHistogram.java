package queue.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/largest-rectangle-in-histogram/description/

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the
area of the largest rectangle in the histogram.
 */
public class LargestRectangleInHistogram {
    public int largestRectangle(int[] heights) {
        int n = heights.length;
        int max = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int area = heights[cur] * (i - left - 1);
                max = Math.max(max, area);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int area = heights[cur] * (n - left - 1);
            max = Math.max(max, area);
        }

        return max;
    }

    public int largestRectangle_array(int[] heights) {
        int max = 0;
        int top = 0;
        int n = heights.length;
        int[] stack = new int[100001];

        for (int i = 0; i < heights.length; i++) {
            while (top > 0 && heights[i] <= heights[stack[top - 1]]) {
                int cur = stack[--top];
                int left = top == 0 ? -1 : stack[top - 1];
                int area = heights[cur] * (i - left - 1);
                max = Math.max(max, area);
            }
            stack[top++] = i;
        }

        while (top > 0) {
            int cur = stack[--top];
            int left = top == 0 ? -1 : stack[top - 1];
            int area = heights[cur] * (n - left - 1);
            max = Math.max(max, area);
        }

        return max;
    }
}
