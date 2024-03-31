package two.pointers;

import java.util.Arrays;

/*
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most
one cookie.

Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each
cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content.
Your goal is to maximize the number of your content children and output the maximum number.
========================================================================================================================
Clarification:
1. child count = M, cookie count = N
2. M and N are >= 1

Solution 1: Greedy and two pointers
Sort input s and g
assign smaller cookie to child with smaller greed factor.
Time: O(M*logM + N*logN)

Optimization: Binary search
In solution 1, we iterate the cookies to find one s[j] >= g[i]
Since we already sort the cookies size increasingly,
we want to find a number (cookie) that is right >= the current greed factor.
The target is g[i], we use binary search to find left boundary of the target.
Time: O(M*logM + N*logN)
The searching (assign process) is optimized

 */
public class AssignCookies {
    public int assign(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i;
    }

    public int assignByBinary(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int start = 0;
        while (i < g.length) {
            int l = search(s, start, g[i]);
            if (l == -1) {
                break;
            }
            start = l + 1;
            i++;
        }
        return i;
    }

    private int search(int[] s, int start, int target) {
        int ans = -1;
        int l = start;
        int r = s.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (s[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
