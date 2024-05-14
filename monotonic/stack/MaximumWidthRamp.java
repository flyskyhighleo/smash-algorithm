package monotonic.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/maximum-width-ramp/description/
A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.
 */
public class MaximumWidthRamp {

    // nums: [6, 0, 8, 2, 1, 5]
    //        0, 1, 2, 3, 4, 5
    //           j
    // stack [0, 1]

    // maxRam = 4


    public int maximumWidthRamp(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
            }
        }

        int maxRamp = 0;
        int j = nums.length - 1;
        while (j >= 0 && !stack.isEmpty()) {
            while (!stack.isEmpty() && nums[j] >= nums[stack.peek()]) {
                int i = stack.pop();
                maxRamp = Math.max(maxRamp, j - i);
            }
            j--;
        }

        return maxRamp;
    }
}
