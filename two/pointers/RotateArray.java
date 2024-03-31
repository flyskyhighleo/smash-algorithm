package two.pointers;

/*
https://leetcode.com/problems/rotate-array/description/
Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
e.g.
[1,2,3,4,5,6,7] k = 3
[7,1,2,3,4,5,6]
[6,7,1,2,3,4,5]
[5,6,7,1,2,3,4]

1. Brute force
shift one unit to right
Time: O(nk)
Space: O(1)

2. With extra space
[1,2,3,4,5,6,7] k = 3
k %= n
two pointers, p and q.
Initialize i = n - k, j = 0

3. cyclic replacement
Similar approach with sol.2. Move the number at index i to the next index (i + k) % n
While moving numbers, keep track count of numbers moved.
when count is the total number count, stop moving.
We start from a position 'start', move numbers until get back to 'start'

4. reverse
[1,2,3,4,5,6,7] k = 3
reverse all: [7,6,5,4,3,2,1]
reverse top k: [5,6,7,4,3,2,1]
reverse last n - k: [5,6,7,1,2,3,4]
 */
public class RotateArray {

    // Brute force
    public void rotateArrayBruteForce(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            for (int j = n - 1; j - 1 >= 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }

    // Two pointers with extra space
    public void rotateArrayExtraSpace(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }
        System.arraycopy(temp, 0, nums, 0, n);
    }

    // Cyclic replacement
    public void rotateArrayCyclic(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for (int start = 0; count < n; start++) {
            int p = start;
            int prev = nums[start];
            do {
                p = (p + k) % n;
                int temp = nums[p];
                nums[p] = prev;
                prev = temp;

                count++;
            } while (p != start);
        }
    }

    // reverse
    // [1,2,3,4,5,6] k = 2
    // reverse all          [6,5,4,3,2,1]
    // reverse first two    [5,6,4,3,2,1]
    // reverse last four    [5,6,1,2,3,4]
    // define a function void reverse(int[] nums, int start, int end) to reverse nums[start, end]
    // apply this function 3 times

    public void rotateArrayReverse(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return;
        }
        k = k % n;
        // verify n == 0 ? or k == 0?
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int l = start;
        int r = end;
        while (l < r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            l++;
            r--;
        }
    }
}
