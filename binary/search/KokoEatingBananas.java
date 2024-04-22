package binary.search;

/*
https://leetcode.com/problems/koko-eating-bananas/description/
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and
will come back in h hours.
Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas
from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas
during this hour.
Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
Return the minimum integer k such that she can eat all the bananas within h hours.
========================================================================================================================
Solution: Binary Search
1. variable: eating speed k, k is in [1, max pile]
2. f(k), time spent, is monotonically decreasing against k.
3. constrains: h hours
 */
public class KokoEatingBananas {

    // piles; [5,2,7,3,1,5,2]
    // left = 1
    // right = 7
    // mid = 4, time = 2 + 1 + 2 + 1 + 1 + 1 + 1 = 9
    // mid = 3, time = 2 + 1 + 2 + 1 + 1 + 2 + 1 = 10

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = -1;
        int res = -1;
        for (int count : piles) {
            right = Math.max(right, count);
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            long time = timeSpent(piles, mid);
            if (time > h) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }

        return res;
    }

    private long timeSpent(int[] piles, int k) {
        int need = 0;
        for (int count : piles) {
            need += (count + k - 1) / k;
        }
        return need;
    }
}
