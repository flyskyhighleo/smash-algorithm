package two.pointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/sum-of-distances/description/
You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i] is the sum
of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

Return the array arr.
========================================================================================================================
Sol: with hash map. save indices of identical number

improve by prefix sum
a, b, c, d, e, f, g, h
left = (d-c) + (d-b) + (d-a)
     = 3 * d - (a + b + c)
right = (e-d) + (f-d) + (g-d) + (h-d)
      = (e + f + g + h) - 4 * d

==> prefix sum


 */
public class SumOfDistances {
    public int[] distance(int[] nums) {
        Map<Integer, List<Integer>> numberToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> index = numberToIndex.getOrDefault(nums[i], new ArrayList<>());
            index.add(i);
            numberToIndex.put(nums[i], index);
        }

        Map<Integer, int[]> numToPreSum = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : numberToIndex.entrySet()) {
            int key = entry.getKey();
            List<Integer> value = entry.getValue();
            int[] preSum = new int[value.size() + 1];
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
            numToPreSum.put(key, preSum);
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < res.length; i++) {
            int sum = 0;
            List<Integer> index = numberToIndex.get(nums[i]);
            int start = search(index, i);

            int[] preSum = numToPreSum.get(nums[i]);

            int left = start * i - preSum[start];
            int right = preSum[preSum.length - 1] - preSum[start + 1] - (index.size() - start - 1) * i;
            sum = left + right;
//
//            int p = start;
//            int q = start;
//
//            while (p >= 0 || q < index.size()) {
//                int left = p >= 0 ? Math.abs(index.get(p) - index.get(start)) : 0;
//                int right = q < index.size() ? Math.abs(index.get(q) - index.get(start)) : 0;
//                sum = sum + left + right;
//                p--;
//                q++;
//            }

            res[i] = sum;
        }

        return res;
    }

    private int search(List<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) == target) {
                return mid;
            } else if (nums.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
