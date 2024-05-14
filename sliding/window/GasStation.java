package sliding.window;

/*
https://leetcode.com/problems/gas-station/description/
There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next
(i + 1)th station. You begin the journey with an empty tank at one of the gas stations.

Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once
in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int left = 0;
        int right = 0;
        int visited = 0;
        int n = gas.length;
        int sum = 0;

        int gasSum = 0;
        int costSum = 0;
        for (int i = 0; i < n; i++) {
            gasSum += gas[i];
            costSum += cost[i];
        }

        if (gasSum < costSum) {
            return -1;
        }

        while (left < n) {
            sum += gas[right] - cost[right];
            right = right == n - 1 ? 0 : right + 1;
            if (sum >= 0) {
                visited++;
                if (visited == n) {
                    return left;
                }
            } else {
                left = right;
                sum = 0;
            }
        }

        return -1;
    }
}
