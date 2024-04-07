package np;

import java.util.*;

/*
https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/

You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to
minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the
two arrays.

Return the minimum possible absolute difference.
========================================================================================================================

a0 + a1 + ... + a_n-1 = sum
partition the integer array into two set, set 1 and set 2.

s1 + s2 = sum
==> s2 = sum - s1
==> |s1 - s2| = |2 * s1 - sum|
s1 = sum of half size numbers

goal is to minimize |2 * s1 - sum|

for a specific number a_i, there're two options:
1. pick in set 1
2. not pick in set 1 (in set 2)

define a function: given input array, nums[0...i] and targetSize, return the min absolute difference of
"2 * curSum - targetSum"
    int minAbsDiff(int[] nums, int i, int k, int curSum, int totalSum):

Goal: minAbsDff(nums, n - 1, n/2, 0, sum / 2)
base case:
i < 0: return max value
k <= 0: return max value

Note: we are not able to find sub-problem from origin problem, therefore, we can't use memory to reduce duplicate calculation
This solution is actually 'backtracking'.
For each number 'a_i' we try: pick, not pick and compare the result.

Let's modify it to 'traditional' backtracking code pattern.
Time complexity if O(2^n). Space O(1)
We generate all sub-arrays. An array with size N has 2^N - 1 sub-arrays. Each number has two options:
1. in the sub-array, 2 not in the sub-array.
========================================================================================================================
Improvement: Meet in the middle
Let's split the input array into two sides, such that each side has N/2 numbers.
because 2^(N/2) - 1 + 2^(N/2) - 1 is way less than 2^N - 1, we improve the time complexity.

Split input array [a0, a1, a2, ... a_n-1] into
s1 = [a0, a1 ... a_k], where k = (n - 1) / 2
s2 = [a_k+1, a_k+2, ... a_n-1]

Let's generate sub-arrays with size of (n/2) from set 1 and set 2:
1. (n/2) from s1, all numbers are from s1
2. 1 number from s1, the rest (n/2-1) numbers are from s2
3. 2 number from s1, the rest (n/2-2) numbers are from s3
4. ...
5. (n/2) from s2, all numbers are from s2

Use a HashMap to save number count and sum of those numbers
Map<Integer, List<Integer>> countToSums

Build a map from left half side, countToSumsLeft
Build a map from right half side, countToSumRight

Once we have the maps, we iterate the entry of one map, and find the corresponding in the other.
Example:
N = 6
count from left = 2, sums are s1, s2, s3
count from right = N/2-countFromLeft, s1',s2',s3'
find min abs difference from  |s1-s1'|...

 */
public class PartitionArrayToMinimizeSumDifference {

    // meet in the middle
    public int minSumDifferenceMeetInTheMiddle(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }

        Map<Integer, List<Integer>> countToSumsLeft = new HashMap<>();
        Map<Integer, List<Integer>> countToSumsRight = new HashMap<>();
        backtrack(nums, 0, n / 2 - 1, 0, 0, countToSumsLeft);
        backtrack(nums, n / 2, n - 1, 0, 0, countToSumsRight);

        int res = Integer.MAX_VALUE;

        for (int i = 0; i <= n / 2; i++) {
            List<Integer> leftSums = countToSumsLeft.get(i);
            List<Integer> rightSums = countToSumsRight.get(n / 2 - i);

//            for (int j = 0; j < leftSums.size(); j++) {
//                for (int p = 0; p < rightSums.size(); p++) {
//                    int halfSum = leftSums.get(j) + rightSums.get(p);
//                    int otherHalfSum = sum - halfSum;
//                    res = Math.min(res, Math.abs(halfSum - otherHalfSum));
//                    res = Math.min(res, Math.abs(2 * halfSum - sum));
//                }
//            }

            // improvement: binary search
            // if 2 * halfSum is already larger than sum, we don't need to check other larger half sum.
            // sort left sums and right sums.
            // use two pointers, as "2-sum" problem
            Collections.sort(leftSums);
            Collections.sort(rightSums);

            int l = 0;
            int r = rightSums.size() - 1;

            while(l < leftSums.size() && r >= 0){
                int halfSum = leftSums.get(l) + rightSums.get(r);
                int otherHalfSum = total - halfSum;
                res = Math.min(res, Math.abs(halfSum - otherHalfSum));
                if(halfSum > total / 2) {
                    r--;
                } else {
                    l++;
                }
            }
        }

        return res;
    }

    private void backtrack(int[] nums,
                           int start,
                           int end,
                           int sum,
                           int size,
                           Map<Integer, List<Integer>> countToSums) {
        if (start > end) {
            List<Integer> sums = countToSums.getOrDefault(size, new ArrayList<>());
            sums.add(sum);
            countToSums.put(size, sums);
            return;
        }

        // pick nums[start]
        backtrack(nums, start + 1, end, sum + nums[start], size + 1, countToSums);
        backtrack(nums, start, end, sum, size, countToSums);
    }


    // backtracking. generate all possible sub-arrays
    public int minSumDifference(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        return minAbsDiff(nums, n - 1, n / 2, 0, totalSum);
    }

    private int minAbsDiff(int[] nums, int i, int k, int curSum, int totalSum) {
        if (k == 0) {
            return Math.abs(2 * curSum - totalSum);
        }

        if (i < 0) {
            return Integer.MAX_VALUE;
        }

        int pick = minAbsDiff(nums, i - 1, k - 1, curSum + nums[i], totalSum);
        int notPick = minAbsDiff(nums, i - 1, k, curSum, totalSum);

        return Math.min(pick, notPick);
    }

    private List<Integer> sums = new ArrayList<>();

    public int minSumDifferenceSol2(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        backtrack(nums, 0, 0, 0, sums);
        int res = Integer.MAX_VALUE;
        for (Integer sum : sums) {
            res = Math.min(res, Math.abs(2 * sum - totalSum));
        }

        return res;
    }

    // collect all sums of subarrays whose size is n / 2
    private void backtrack(int[] nums, int i, int curSum, int size, List<Integer> sums) {
        if (size == nums.length / 2) {
            sums.add(curSum);
            return;
        }

        curSum += nums[i];
        size += 1;
        backtrack(nums, i, curSum, size, sums);
        curSum -= nums[i];
        size -= 1;
    }
}
