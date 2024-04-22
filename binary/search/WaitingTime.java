package binary.search;

/*
There're n waiters in a restaurant.
waiters[i] represent the ith waiter serving time for a guest.
You're waiting in a line, with m guests ahead of you.
How long have you to wait to be served?
========================================================================================================================
Binary search for Answer.
Let the waiting time be x.
x range is [0, min value * m]

Define a function int served(int[] waiters, int x) returns number of guested served given time x.
monotonic relation: x increases, served count increase. => monotonically increasing.

Binary Search for Answer.
if served > m + 1, x is too large, search left.
if served < m + 1, x is too small, search right. (can serve only k guests, k < m + 1)
if served = m + 1, there might exist smaller answer, search left.

 */
public class WaitingTime {
    public int waitingTime(int[] waiters, int m) {
        int left = 0;
        int right = 0;
        int min = 0;
        for (int waiter : waiters) {
            min = Math.min(min, waiter);
        }
        right = min * m;

        int res = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int count = servedCount(waiters, mid);
            if (count < m + 1) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }

        return res;
    }

    private int servedCount(int[] waiters, int time) {
        int count = 0;
        for (int waiter : waiters) {
            count += waiter / time + 1;
        }
        return count;
    }
}
