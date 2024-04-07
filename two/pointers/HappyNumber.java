package two.pointers;

/*
https://leetcode.com/problems/happy-number/description/

Write an algorithm to determine if a number n is happy.
A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not
include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.
========================================================================================================================
12,5,25,29,85,89,145,42,20,4,16,37,58,89...
When apply the process, the number:
1. ends to 1
2. in a cycle
3. endless (won't happen)
why case 3 won't happen?
digit | max | square sum
1       9       81
2       99      162
3       999     243
4       9999    324
5       99999   405

the square sum is smaller than the number when digit count >= 3
e.g. when digit count = 3, the ceiling of square sum is 243. that means, the squares
sum won't exceed 243 and eventually go back to a number less or equal to 243. Therefore,
eventually ends into a cycle.
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);

        return fast == 1;
    }

    private int getNext(int n) {
        int next = 0;
        while (n > 0) {
            next = next + n % 10;
            n = n / 10;
        }
        return next;
    }
}
