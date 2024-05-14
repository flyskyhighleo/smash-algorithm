package monotonic.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/daily-temperatures/description/
Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i]
is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for
which this is possible, keep answer[i] == 0 instead.
=======================================================================================================================
Solution:
find the closest index on the right such that the number at that index is > temp[i]
Iterate from end to start
Monotonic stack: top: small, bot: large
for the current element t[i]
if stack is empty, no days warmer. enStack i
while stack is not empty and stack top is less than t[i], pop
the current top is the warmer day

 */
public class DailyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= temperatures[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return res;
    }

    // left to right
    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int cur = stack.pop();
                res[cur] = i - cur;
            }
            stack.push(i);
        }
        return res;
    }

    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;
        int hotest = 0;
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            int cur = temperatures[i];
            if (cur >= hotest) {
                hotest = cur;
                continue;
            }

            int days = 1;
            while (cur <= temperatures[i + days]) {
                days += res[i + days];
            }
        }

        return res;
    }
}
