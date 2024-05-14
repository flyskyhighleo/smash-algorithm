package monotonic.stack;

import java.util.Stack;

/*
有N条鱼每条鱼的位置及大小均不同，他们沿着X轴游动，有的向左，有的向右。游动的速度是一样的，两条鱼相遇大鱼会吃掉小鱼。
从左到右给出每条鱼的大小和游动的方向（0表示向左，1表示向右）。问足够长的时间之后，能剩下多少条鱼？
 */
public class AlwaysABiggerFish_I {
    public int alwaysABiggerFish(int[] weights, int[] directions) {
        int n = weights.length;
        int kill = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int weight = weights[i];
            int dir = directions[i];

            if (dir == 1) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && weights[stack.peek()] < weight) {
                    stack.pop();
                    kill++;
                }
                if (!stack.isEmpty()) {
                    kill++;
                }
            }
        }

        return n - kill;
    }
}
