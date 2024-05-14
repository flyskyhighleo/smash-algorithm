package sliding.window;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/subarrays-with-k-different-integers/description/
Given an integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.
=======================================================================================================================
Sometimes it's not straightforward to find with explicit k.
If we want to find explicit k, we can find <= k and <= k - 1.
Define a function f(nums, k) that find subarrays whose unique numbers are less or equal to k (<=k)
To find explicit k, f(k) - f(k - 1)
 */
public class SubArraysWithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return findSubarraysAtMostK(nums, k) - findSubarraysAtMostK(nums, k - 1);
    }

    private int findSubarraysAtMostK(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int unique = 0;
        int res = 0;
        Map<Integer, Integer> window = new HashMap<>();

        while (right < nums.length) {
            int r = nums[right++];
            window.put(r, window.getOrDefault(r, 0) + 1);
            if (window.get(r) == 1) {
                unique++;
            }

            while (unique > k) {
                int l = nums[left++];
                window.put(l, window.get(l) - 1);
                if (window.get(l) == 0) {
                    unique--;
                }
            }

            res += right - left;
        }

        return res;
    }
}
