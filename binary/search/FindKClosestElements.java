package binary.search;

import java.util.LinkedList;
import java.util.List;

/*
https://leetcode.com/problems/find-k-closest-elements/description/

Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b
 */
public class FindKClosestElements {
    public List<Integer> findKClosest(int[] nums, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();
        int right = search(nums, x);
        int left = right - 1;

        while ((left >= 0 || right < nums.length) && k > 0) {
            int leftDist = left >= 0 ? Math.abs(nums[left] - x) : Integer.MAX_VALUE;
            int rightDist = right < nums.length ? Math.abs(nums[right] - x) : Integer.MAX_VALUE;
            if (leftDist <= rightDist) {
                res.addFirst(leftDist);
                left--;
            } else {
                res.add(rightDist);
                right++;
            }
            k--;
        }

        return res;
    }

    private int search(int[] nums, int x) {
//        int res = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < x) {
                left = mid + 1;
            } else {
//                res = mid;
                right = mid - 1;
            }
        }
        return left;
    }
}