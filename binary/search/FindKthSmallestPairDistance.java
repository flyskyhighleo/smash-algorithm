package binary.search;

import java.util.Arrays;

/*
https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/
The distance of a pair of integers a and b is defined as the absolute difference between a and b.
Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j]
where 0 <= i < j < nums.length.
========================================================================================================================
nums = [a0, a1, a2,...an-1]
Naive Approach:
we can generate all pairs then sort by distance.
> O(N^2 + NlogN)

Binary Search for Answer
nums = [a0, a1, a2,...an-1]
min pair distance = 0
max pair distance = max value - min value
Does monotonic relation exist?
As pair distance increases, the pair count <= distance will increase!!!
e.g.
when pair distance is max, max value - min value, the pair count <= maxDist is all pairs in the array.
when pair distance is 0, the pair count <= minDist is 0. (Assume all values are distinct)

Let's define a function f(x, disLimit) that returns pair count <= disLimit
Goal is to find kth smallest distance.

If the returned pair count < k, distLimit is too small, search right.
If the returned pair count > k, distLimit is too large. Under the distance limit, there must exist more than k satisfied pairs.
    search left.
If the returned pair count == k, there might exit smaller distance limit. search left

 */
public class FindKthSmallestPairDistance {
    private int kthSmallestPairDistance(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];

        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (pairCount(nums, mid) < k) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }

        return res;
    }

    private int pairCount(int[] nums, int distLimit) {
        int left = 0;
        int right = 0;
        int count = 0;

        while (left < nums.length) {
            while (right < nums.length && nums[right] - nums[left] <= distLimit) {
                right++;
            }
            count += (right - left - 1);
            left++;
        }

        return count;
    }
}
