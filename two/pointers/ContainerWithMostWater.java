package two.pointers;

/*
https://leetcode.com/problems/container-with-most-water/
You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of
the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.
************************************************************************************************************************
two pointers. l and r
water is determined by lower height.
l...r, water = min(l, r) * (r - l)
dynamically update max water

 */
public class ContainerWithMostWater {

    // [1,4,2,7,3]
    //  0 1 2 3 4
    public int maxWater(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;

        while (l < r) {
            int cur = Math.min(height[l], height[r]) * (r - l);
            max = Math.max(max, cur);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return max;
    }
}
