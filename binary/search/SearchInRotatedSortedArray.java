package binary.search;

/*
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such
that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums,
or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.
=======================================================================================================================
Solution: Binary Search
Rule: apply binary search on the side where target must exist!!!
After get the middle point, shrink the searching range.

         /
        /
       /|
        |      /
        |     /
        |    /|
        |   / |
        |  /  |
--------|-----|--------------
    A   M B C M D

the mid point 'm' could be on A-B or C-D
if m is on A-B, determine the searching area for A-M and M-B and the corresponding conditions
if m is on C-D, determine the searching area for C-M and M-D and the corresponding conditions
 */
public class SearchInRotatedSortedArray {

    private int searchOptimization(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int first = nums[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] >= first) {
                // on A-B
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // on C-D
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    private int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int first = nums[0];
        boolean onLargeSide = target >= first;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (onLargeSide) {
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    if (nums[mid] < first) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    if (nums[mid] >= first) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}
