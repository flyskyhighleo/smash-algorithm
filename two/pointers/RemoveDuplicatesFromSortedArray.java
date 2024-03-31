package two.pointers;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element
appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements
in nums.
Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
Change the array nums such that the first k elements of nums contain the unique elements in the order they were present
in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.
************************************************************************************************************************
Solution 1: Two pointers, p and q.
nums[0...p] maintains unique numbers. Move q to find different number.
Move the different number to front.
========================================================================================================================
Solution 2: Binary Search (search right boundary)
[1,1,1,1,2,2,3,3,3,3,3,3]
Input is sorted -> binary search
The next number of right boundary must be a different number.
NOTE: rightBoundary + 1 might be out of range.

 */
public class RemoveDuplicatesFromSortedArray {

    // [1,1,2,3,3]
    public int findUnique(int[] nums) {
        int p = 0;
        int q = 0;
        if (nums.length == 0) {
            return 0;
        }
        while (q < nums.length) {
            if (nums[q] != nums[p]) {
                p++;
                nums[p] = nums[q];
            }
            q++;
        }
        return p + 1;
    }

    public int findUniqueWithoutModifying(int[] nums) {
        int p = 0;
        int q = 0;
        int unique = 0;
        if (nums.length == 0) {
            return unique;
        }
        while (q < nums.length) {
            if (nums[q] != nums[p]) {
                unique++;
                p = q;
            }
            q++;
        }
        return unique + 1;
    }

    // [1,1,1,1,1,2,2]
    public int findUniqueBinarySearch(int[] nums) {
        int n = nums.length;
        int p = 0;

//        int r = searchRight(nums, nums[p]);
        int r = searchRight(nums, 0, nums[p]);

        while (r < n - 1) {
            p += 1;
            nums[p] = nums[r + 1];
//            r = searchRight(nums, nums[p]);
            r = searchRight(nums, r + 1, nums[p]);
        }

        return p + 1;
    }

    // [1,1,1,1,1,2,2]
    private int findUniqueBinarySearchWithoutModifying(int[] nums) {
        int n = nums.length;
        int unique = 0;
        if (n == 0) {
            return 0;
        }
        int target = nums[0];

        int r = searchRight(nums, 0, target);

        while (r < n - 1) {
            target = nums[r + 1];
            unique += 1;
            r = searchRight(nums, r + 1, target);
        }

        return unique + 1;
    }

    private int searchRight(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    private int searchRight(int[] nums, int start, int target) {
        int l = start;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }
}
