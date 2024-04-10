package two.pointers;

/*
https://leetcode.com/problems/apply-operations-to-an-array/description/

You are given a 0-indexed array nums of size n consisting of non-negative integers.

You need to apply n - 1 operations to this array where, in the ith operation (0-indexed), you will apply the following
on the ith element of nums:

If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1] to 0. Otherwise, you skip this operation.
After performing all the operations, shift all the 0's to the end of the array.

For example, the array [1,0,2,0,0,1] after shifting all its 0's to the end, is [1,2,1,0,0,0].
Return the resulting array.

Note that the operations are applied sequentially, not all at once.
========================================================================================================================
Sol 1: two loops. apply operation then move zeros
Time: O(n)

Sol 2: One loop. maintain a pointer k, such that nums[0...k] are non-zeros
for the current number nums[i], if it's not zero and nums[i] == nums[i + 1]
apply the operations.
We improve it by applying the operation only to non-zero numbers

 */
public class ApplyOperationsToAnArray {
    public int[] applyOperations(int[] nums) {
        for (int i = 0; i + 1 < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] = 2 * nums[i];
                nums[i + 1] = 0;
            }
        }

        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
                i++;
            }
            j++;
        }

        return nums;
    }

    public int[] sol2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                    nums[k] = nums[i];
                } else {
                    nums[k] = nums[i];
                }
                k++;
            }
        }

        for (int j = k; j < nums.length; j++) {
            nums[j] = 0;
        }

        return nums;
    }
}
