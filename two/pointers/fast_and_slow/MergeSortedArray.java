package two.pointers.fast_and_slow;

/*
https://leetcode.com/problems/merge-sorted-array/description/
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be
merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

array1: a0,a0,a1,b1,a2
      i, p
array2: b0,b1
        j

two pointers, i, j
initialize i = m - 1, j = n -1
while (i >= 0 && j >= 0)
start from the end of nums1
put larger number at p,
move larger pointer to left, move p to left

if j >= =0, append all nums2 from j to left

*/
public class MergeSortedArray {
    public void merge(int[] nums1, int[] nums2) {
        int p = nums1.length - 1;
        int i = nums1.length - nums2.length - 1;
        int j = nums2.length - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[p--] = nums1[i--];
            } else {
                nums1[p--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[p--] = nums2[j--];
        }
    }
}
