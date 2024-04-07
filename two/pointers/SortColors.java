package two.pointers;

/*
https://leetcode.com/problems/sort-colors/description/
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are
adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.
=======================================================================================================================
We want to put all 0s on the left and all 2s on the right side of the array.
Maintain two pointers left and right such that left indicate a position where 0 should put, and right pointer indicates
a position where 2 should be.
Use a pointer p to operate the current number
if n[p] is 2, swap it with right
else if n[p] is 0, swap it with left
after we move all 0 to the left and all 2 to the right,
1 will stay in the middle of the array.
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int cur = 0;

        while (cur <= right) {
            if (nums[cur] == 2) {
                swap(nums, cur, right);
                right -= 1;
            } else if (nums[cur] == 0) {
                swap(nums, cur, left);
                left += 1;
                cur += 1;
            } else {
                cur += 1;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
