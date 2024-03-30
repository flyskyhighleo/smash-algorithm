package two.pointers.left_and_right;

/*
https://leetcode.com/problems/remove-element/description/

Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
The remaining elements of nums are not important as well as the size of nums.

Return k.
========================================================================================================================
Sol 1: sort and override
Time: O(NlogN)

Sol 2: swap the target with last element.
The last element should be different with the target
Approach: two pointers, left and right

 */
public class RemoveElement {

    // [2,3,2,1,1,1,1] val = 1
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        if (nums.length == 0) {
            return 0;
        }
        while (left <= right) {
            if (nums[left] == val) {
                while (left <= right && nums[right] == val) {
                    right--;
                }
                if (left < right) {
                    swap(nums, left, right);
                }
            } else {
                left++;
            }
        }

        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
