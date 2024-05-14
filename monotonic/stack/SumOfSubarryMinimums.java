package monotonic.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/sum-of-subarray-minimums/description/
Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
Since the answer may be large, return the answer modulo 10^9 + 7.
=======================================================================================================================
Given a subarry of array numbs, sub
sub = [...a_i...]
suppose a_i is the smallest number in the subarray.
How to compute the sum of min(b)?
1. count sub-arrays in the subarray that has a_i.
2. count * a_i

Suppose there're x elements on the left side of a_i, and y elements on the right side of a_i
count = x * y
therefore sum = x * y * a_i

How to get the subarray [...a_i...]?
all elements on the left side > a_i
all elements on the right side > a_i
==> monotonic stack is used to
1. find the smaller right most element index on the left side
2. find the smaller left most element index on the right side

Iterate all elements and sum up.
 */
public class SumOfSubarryMinimums {
    public int sumOfSubarrayMin(int[] arr) {
        long res = 0;
        int n = arr.length;
        int mod = 1000000007;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                int cur = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = (res + (long) (cur - left) * (i - cur) * arr[cur]) % mod;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            res = (res + (long) (cur - left) * (n - cur) * arr[cur]) % mod;
        }

        return (int) res;
    }
}
