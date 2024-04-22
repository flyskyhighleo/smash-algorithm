package binary.search;

/*
https://leetcode.com/problems/maximum-running-time-of-n-computers/description/
You have n computers. You are given the integer n and a 0-indexed integer array batteries where the ith battery can run
a computer for batteries[i] minutes. You are interested in running all n computers simultaneously using the given batteries.

Initially, you can insert at most one battery into each computer. After that and at any integer time moment, you can
remove a battery from a computer and insert another battery any number of times. The inserted battery can be a totally
new battery or a battery from another computer. You may assume that the removing and inserting processes take no time.

Note that the batteries cannot be recharged.

Return the maximum number of minutes you can run all the n computers simultaneously.
=======================================================================================================================
Let the simultaneous running time be x.
1. Assume there're at least n batteries that has energy >= x, those batteries are enough to run n computers for x minutes simultaneously.
2. Assume non of the batteries ahd energy >= x, assume

batteries: [7,3,2,4,5], x = 8, n = 3
    0       7 8
c0: |-------|-|
    b0      b1
    0  2  4    8
c1: |--|--|----|
    b1 b2  b3
    0     5
c2: |-----|
    b4
The batteries can not run n = 3 computers for 8 minutes simultaneously.

    index:  0,1,2,3,4
batteries: [5,4,2,4,5], x = 6, n = 3
    0     5 6
c0: |-----|-|
    b0    b1
    0   3  5 6
c1: |---|--|-|
    b1 b2  b3
    0   3   6
c2: |---|---|
    b3  b4
The batteries can run n = 3 computers for 4 minutes simultaneously.

Fact: Assume non of batteries has energy >= x, if sum of energy is larger or equal to x * n, the batteries are able to
      run n computers for x minutes.

3. Assume not all batteries have energy >= x.
   Use batteries >= x to run computers. Use the rest to run other computers.
   Back to Assumption 2.

Algorithm:
1. let the simultaneous running time be k
2. k is in range [0, max energy].
3. define a function boolean canRun(int[] batteries, int x), true: n computers can run for x minutes simultaneously.
    false: can not run
4. monotonic relation: k increases, harder to run. ==> monotonic increasing
5. binary search for answer.
 */
public class MaximumRunningTimeOfNComputers {
    public long maxRunTime(int n, int[] batteries) {
        int max = 0;
        long energySum = 0;
        for (int battery : batteries) {
            energySum += battery;
            max = Math.max(battery, max);
        }

        if (energySum >= (long) n * max) {
            return energySum / n;
        }

        int left = 0;
        int right = max;
        int res = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (canRun(batteries, n, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    private boolean canRun(int[] batteries, int n, int time) {
        long sum = 0;
        for (int battery : batteries) {
            if (battery > time) {
                n--;
            } else {
                sum += battery;
            }
            if (sum >= (long) n * time) {
                return true;
            }
        }
        return false;
    }
}
