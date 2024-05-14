package sliding.window;

/*
https://leetcode.com/problems/minimum-size-subarray-sum/description/

Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose
sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 */
public class MinimumSizeSubarraySum {
    public int minSizeSubarraySum(int[] nums, int target) {
        int left = 0;
        int right = 0;
        int windowSum = 0;
        int res = Integer.MAX_VALUE;

        while (right < nums.length) {
            windowSum += nums[right++];
            while (windowSum >= target) {
                res = Math.min(res, right - left);
                windowSum -= nums[left++];
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
