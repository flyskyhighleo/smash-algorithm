package two.pointers.fast_and_slow;

/*
https://leetcode.com/problems/move-zeroes/description/
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.
************************************************************************************************************************
Two pointers, p, q.
nums[0...p] maintains non-zero number
move q to find non-zero number. If find, put it at index p. p++

 */
public class MoveZeros {

    // [1,2,0,3,2,0,4]
    public void moveZeros(int[] nums) {
        int p = 0;
        int q = 0;
        while (q < nums.length) {
            if (nums[q] != 0) {
                nums[p] = nums[q];
                p++;
            }
            q++;
        }
        while (p < nums.length) {
            nums[p] = 0;
            p++;
        }
    }
}
