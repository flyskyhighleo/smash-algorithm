package monotonic.stack;

/*
Given an array nums, return an answer array, such that res[i][0] is the index
1. < nums[i]
2. on the left side of nums[i]
3. right most
and res[i][1] is the index
1. > nums[i]
2. on the right side of nums[i]
3. left most
 */

import java.util.Stack;

public class MyMonotonicStack {
    public int[][] monotonicStack(int[] nums) {
        int n = nums.length;
        int[][] res = new int[n][2];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                int topIndex = stack.pop();
                res[topIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                res[topIndex][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int topIndex = stack.pop();
            res[topIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            res[topIndex][1] = -1;
        }

        for (int i = n - 1; i >= 0; i--) {
            if (res[i][1] != -1 && nums[i] == nums[res[i][1]]) {
                res[i][1] = res[res[i][1]][1];
            }
        }

        return res;
    }

    public int[] findOnRightSide(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return res;
    }
}
