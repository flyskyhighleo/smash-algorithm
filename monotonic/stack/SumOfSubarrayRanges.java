package monotonic.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/sum-of-subarray-ranges/description/
You are given an integer array nums. The range of a subarray of nums is the difference between the largest and smallest
element in the subarray.
Return the sum of all subarray ranges of nums.
A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class SumOfSubarrayRanges {
    public long subArrayRanges(int[] nums) {
        long minSum = 0;
        long maxSum = 0;
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        // compute min sum
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                minSum += (long) (cur - left) * (i - cur) * nums[cur];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            minSum += (long) (cur - left) * (n - cur) * nums[cur];
        }

        // compute max sum
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                maxSum += (long) (cur - left) * (i - cur) * nums[cur];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            maxSum += (long) (cur - left) * (n - cur) * nums[cur];
        }

        return maxSum - minSum;
    }
}
