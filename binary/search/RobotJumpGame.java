package binary.search;

/*
A robot is playing an ancient DOS-based game. There are N buildings in the game—numbered from 0 to N - 1, arranged from
left to right. The building numbered 0 has a height of 0 units, while the building numbered i has a height of H[i] units.

Initially, the robot is at the building numbered 0. At each step, it jumps to the next (rightward) building.
Assuming the robot is currently at the kth building and its current energy level is E, the next step will take it to
building k+1.
It will either gain or lose energy proportional to the difference between H[k+1] and E. If H[k+1] > E,
then the robot will lose H[k+1] - E units of energy; otherwise, it will gain E - H[k+1] units of energy.
The goal of the game is to reach the Nth building, ensuring that the energy level never falls below zero units during
the process.

The question now is, how much energy must the robot start the game with to ensure it can successfully complete the game?
========================================================================================================================
Assume the robot's energy is the max height, then the robot can definitely complete the game.
Assume the robot's energy is 0, the robot may complete the game. (if each height is 0)
Therefore, the robot energy is [0, max height]

Define a function f(x) that tell max jumps the robot can make given initial energy x
f(x) is monotonically increasing against x.

Binary search the energy range [0, max height]
given an energy value
if can complete, search left because energy might be too big
if can not complete, search right, because energy is too small
 */
public class RobotJumpGame {
    public int minEnergy(int[] height) {
        int left = 0;
        int right = 0;
        int maxEnergy = 0;
        for (int h : height) {
            right = Math.max(h, right);
            maxEnergy = Math.max(maxEnergy, h);
        }

        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canComplete(height, mid, maxEnergy)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean canComplete(int[] height, int energy, int maxEnergy) {
        for (int i = 0; i < height.length; i++) {
            if (energy < 0) {
                return false;
            }
            if (energy >= maxEnergy) {
                return true;
            }
            energy += (energy - height[i]);
        }
        return true;
    }
}
