package binary.search;

/*
https://leetcode.com/problems/split-array-largest-sum/description/
Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any
subarray is minimized.
Return the minimized largest sum of the split.
A subarray is a contiguous part of the array.
========================================================================================================================
Solution: binary search potential answers
The min sum of subarray is the max number in the array
The max sum of a subarray is the sum of all numbers
Thus, the sum of subarrays is in range [max number, all sum]

Function f(x) return the number of split if largest sum is x
f(x) is monotonically decreasing against x.

Therefore, we can apply binary search.
left = min number
right = all sum
if f(mid) > k, left = mid + 1; (too small)
if f(mid) < k, right = mid - 1; (too large)
if f(mid) == k, right = mid - 1; (smaller sum might exist)

 */
public class SplitArrayLargestSum {
    // 1, 4, 4
    // left = 1
    // right = 9
    // mid = 5
    public int minLargestSum(int[] nums, int k) {
        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        int mid = 0;
        int res = -1;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (splitCount(nums, mid) > k) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }

        return res;
    }

    private int splitCount(int[] nums, int sum) {
        int count = 0;
        int i = 0;
        while (i < nums.length) {
            int curSum = 0;
            while (i < nums.length && curSum <= sum) {
                curSum += nums[i];
                i++;
            }

            if (curSum > sum) {
                i--;
            }
            count++;
        }
        return count;
    }
}
