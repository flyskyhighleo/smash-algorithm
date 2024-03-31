package two.pointers;

/*
https://leetcode.com/problems/next-permutation/description/

A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2,1,3], [2,3,1],
[3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
More formally, if all the permutations of the array are sorted in one container according to their lexicographical
order, then the next permutation of that array is the permutation that follows it in the sorted container.
If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in
ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.
************************************************************************************************************************
Brute force approach:
1. backtracking to find all permutations
2. sort
3. find the next of the given input.
Time complexity: O(n!)

Optimized approach: two pointers.
          x
            x
        x

              x
      x         x
    x
1. use a pointer start from last digit to find the inflection point
2. find the digit to swap by adding 1 to the inflection point, mark as 'toSwap'
3. use a pointer from last digit to find a digit that is larger than 'toSwap', mark as 'replacement'
4. swap 'toSwap' and 'replacement'
5. reverse from the inflection point to the end
 */
public class NextPermutation {
    public int nextPermutation(int[] nums) {
        int p = nums.length - 1;
        while (p - 1 >= 0 && nums[p - 1] >= nums[p]) {
            p--;
        }

        if (p > 0) {
            int toSwap = p - 1;
            int q = nums.length - 1;
            while (q >= p && nums[q] <= nums[toSwap]) {
                q--;
            }
            swap(nums, toSwap, q);
        }

        reverse(nums, p);

        return getValue(nums);
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private void reverse(int[] nums, int start) {
        int p = start;
        int q = nums.length - 1;
        while (p < q) {
            swap(nums, p, q);
            p++;
            q--;
        }
    }

    private int getValue(int[] nums) {
        int val = 0;
        for (int n : nums) {
            val = val * 10 + n;
        }
        return val;
    }
}
