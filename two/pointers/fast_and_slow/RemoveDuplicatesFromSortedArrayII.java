package two.pointers.fast_and_slow;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/
Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique
element appears at most twice. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed
in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the
first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
************************************************************************************************************************
Solution 1: two pointers. p and q.
nums[0...p] maintains unique numbers that appears at most twice.
Move q to find next number (either different or appears once so far) to put at p + 1
maintain a counter for the nums[p].
if nums[q] != nums[p], find different, put to p + 1. count + 1
else if (count < 2) put to p + 1

Solution 2: binary search to find right bound.
follow up: make each unique numbers appears at most k times
nums[0...i] maintains unique number that appears at most k times.
use binary search to find the right boundary of a unique number.
==> count of current unique number
if count < k, repeat count times
if count > k, repeat k times
==> repeat min(k, count) from i + 1 for the current unique number

 */
public class RemoveDuplicatesFromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        int p = 0;
        int q = 0;
        int count = 0;

        while (q < nums.length) {
            if (nums[q] != nums[p] || (q > p && count < 2)) {
                p++;
                nums[p] = nums[q];
                count++;
            }

            q++;

            if (q < nums.length && nums[q] != nums[q - 1]) {
                count = 0;
            }
        }

        return p + 1;
    }

    public int removeDuplicates(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }

        int start = 0;
        int r = searchRight(nums, start, nums[0]);

        int i = 0;

        while (r < nums.length) {
            int count = r - start + 1;
            int repeat = Math.min(count, k);
            for (int t = 0; t < repeat; t++) {
                nums[i] = nums[r];
                i += 1;
            }

            if (r == nums.length - 1) {
                break;
            }

            start = r + 1;
            r = searchRight(nums, start, nums[start]);
        }

        return i;
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

