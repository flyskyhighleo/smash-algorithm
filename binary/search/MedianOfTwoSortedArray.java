package binary.search;

/*
https://leetcode.com/problems/median-of-two-sorted-arrays/description/
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).
 */
public class MedianOfTwoSortedArray {
    public double findMedian(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if ((m + n) % 2 != 0) {
            return find((m + n - 1) / 2, nums1, 0, m - 1, nums2, 0, n - 1);
        } else {
            int midOne = find((m + n - 1) / 2, nums1, 0, m - 1, nums2, 0, n - 1);
            int midTwo = find((m + n) / 2, nums1, 0, m - 1, nums2, 0, n - 1);
            return (midOne + midTwo) / 2.0;
        }
    }

    private int find(int k, int[] nums1, int start1, int end1, int[] nums2, int start2, int end2) {

        // base case
        if (start1 > end1) {
            return nums2[k - start1];
        }

        if (start2 > end2) {
            return nums1[k - start2];
        }

        int mid1 = start1 + (end1 - start1) / 2;
        int mid2 = start2 + (end2 - start2) / 2;

        if (k > mid1 + mid2) {
            if (nums1[mid1] <= nums2[mid2]) {
                // drop A_left
                return find(k, nums1, mid1 + 1, end1, nums2, start2, end2);
            } else {
                // drop B_left
                return find(k, nums1, start1, end1, nums2, mid2+ 1, end2);
            }
        } else {
            if (nums1[mid1] <= nums2[mid2]) {
                // drop B_right
                return find(k, nums1, start1, end1, nums2, start2, mid2 - 1);
            } else {
                // drop A_right
                return find(k, nums1, start1, mid1 - 1, nums2, start2, end2);
            }
        }
    }
}
