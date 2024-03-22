package two.pointers.left_and_right;

/*
https://leetcode.com/problems/trapping-rain-water/description/
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it
can trap after raining.
========================================================================================================================
Focus on specific height, height[i]
The water it can trap is determined by the max height on its left and max height on its right.
e.g.
height[i] = 3
max left height = 5
max right height = 7
the water heights[i]  can trap is 5 - 3 = 2
In general:
water[i] = min (left max, right max) - height[i]
therefore, given a height[i], we need to know max height on both its sides.

pre-calculate max height (left and right) for height[i]
then iterate height in the input, sum up water
========================================================================================================================
Optimization:
In the above approach, we pre-calculate max left and max right.
We can calculate max left and right in real-time.

NOTE: water that a height can trap is determined by min (left max height, right max height)

Use two pointers, left and right, to iterate from left to right and right to left.
calculate maxLeft and maxRight.
if maxLeft <= maxRight, sum water on height[left], move left += 1
else sum water on height[right], move right -= 1

 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        int curMax = 0;
        int[] maxLeft = new int[n];
        for (int i = 0; i < n; i++) {
            curMax = Math.max(curMax, height[i]);
            maxLeft[i] = curMax;
        }

        curMax = 0;
        int[] maxRight = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            curMax = Math.max(curMax, height[i]);
            maxRight[i] = curMax;
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += (Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }

        return water;
    }

    public int trapWithTwoPointers(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax <= rightMax) {
                water += (leftMax - height[left]);
                left += 1;
            } else {
                water += (rightMax - height[right]);
                right -= 1;
            }
        }

        return water;
    }
}
