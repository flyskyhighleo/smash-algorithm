package two.pointers.fast_and_slow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/two-sum/description/
https://leetcode.com/problems/3sum/description/
https://leetcode.com/problems/4sum/description/
2Sum
Given an array of integers nums and an integer target, return pair of the two numbers such that they add up to target.
The answer should avoid duplicates.
e.g. target = 5, nums = [2,4,2,3,2,2,3,1,7]
The answer should be [[2,3],[1,4]]

************************************************************************************************************************
sort the input array increasingly
[1,1,2,2,2,3,3,4,7,7]
two points, p and q, initialize p = 0, q = n - 1
calculate the sum of current p and q
compare the sum with target,
    move pointers p and q according to the comparison.

NOTE: the result should avoid duplicates.
    when we find a valid pair, continue move pointers p and q until reach different numbers

3Sum
a + b + c = t
b + c = t - a
the 3sum problem is now converted to 2Sum problem.
[1,1,2,2,3,3,4,6,6,9], target = 10
use the target number subtracts the number at index i.
The problem becomes find two sum (pair) in the range of [i + 1, n - 1]
The base case is two sum problem.

4Sum
a + b + c + d = t
b + c + d = t - a
when the target number subtracts a number at index i,
this problem becomes 3Sum.

Same for 5Sum, 6Sum ... nSum

Finding:
nSum problem has recursion property. When the target number subtracts a number at index i, it becomes n-1 sum problem.
base case is two sum.

let's define the function which returns number combinations that sum up is target
    List<List<Integer>> nSum(nums, target)

for the current number at index i,
    target - nums[i] -> sub target
    List<List<Integer>> nSum(nums, sub target, start)
    append the current number num[i] to the result of sub problem

 */
public class NSum {

    public List<List<Integer>> nSum(int[] nums, int target, int n) {
        Arrays.sort(nums);
        return nSumHelper(nums, n, target, 0);
    }

    private List<List<Integer>> nSumHelper(int[] nums, int n, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || n > nums.length) {
            return res;
        }

        if (n == 2) {
            return twoSum(nums, target, start);
        } else {
            for (int i = 0; i < nums.length; i++) {
                int cur = nums[i];
                List<List<Integer>> subs = nSumHelper(nums, n - 1, target - cur, i + 1);
                for (List<Integer> sub : subs) {
                    sub.add(cur);
                    res.add(sub);
                }
                while (i + 1 < n && nums[i + 1] == nums[i]) {
                    i++;
                }
            }
        }

        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        int l = start;
        int r = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();

        while (l < r) {
            int left = nums[l];
            int right = nums[r];
            int sum = left + right;
            if (sum == target) {
                List<Integer> pair = new ArrayList<>();
                pair.add(left);
                pair.add(right);
                res.add(pair);
                while (l < r && nums[l] == left) {
                    l++;
                }
                while (l < r && nums[r] == right) {
                    r--;
                }
            } else if (sum < target) {
                l++;
            } else {
                r--;
            }
        }

        return res;
    }

    public List<List<Integer>> twoSum(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        while (l < r) {
            int left = nums[l];
            int right = nums[r];
            int sum = left + right;
            if (sum == target) {
                List<Integer> pair = new ArrayList<>();
                pair.add(left);
                pair.add(right);
                res.add(pair);
                while (l < r && nums[l] == left) {
                    l++;
                }
                while (l < r && nums[r] == right) {
                    r--;
                }
            }
        }

        return res;
    }
}
