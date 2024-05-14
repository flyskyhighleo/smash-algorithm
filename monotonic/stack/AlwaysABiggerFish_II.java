package monotonic.stack;

import java.util.Stack;

/*
现在有 N 条鱼，每条鱼的体积为 Ai，从左到右排成一排。
A 数组是一个排列。 定义一次大鱼吃小鱼的操作为：对于每一条鱼，它在每一次操作时都会吃掉右边比自己小的一条鱼，值得注意的是，在同一次操作中，
每条鱼吃掉比自己小的鱼是同时发生的。
举例：假设有三条鱼，体积分别为 [5, 4, 3]，在一次操作中，4 吃 3，5 吃 4，最终只剩下 [5] 一条鱼。 问题是，在多少次操作之后，鱼的数量就不会变了。
========================================================================================================================
因为“左吃右”，所以从后往前遍历，因为要先知道后边的信息。
单调栈记录入栈元素吃掉（完成）后面的工作需要几步操作，单调栈维持“小压大”性质
由于是“同时发生”，对于当前元素，
1. 当它不能吃掉栈顶元素的时候，它的后续工作为0，入栈。
2. 当它能吃掉栈顶元素的时候，它吃掉栈顶元素需要1不操作，完成后面的工作需要栈顶元素的步骤。例如，当前元素 7， 栈顶元素（5，2），当7吃掉5的时候
（需要1步，它仍然需要2步来完成5后面的工作。所以（7，2）入栈。 如果当前元素是3，（3，0）直接入栈

 */
public class AlwaysABiggerFish_II {
    public int operationCount(int[] weights) {
        int res = 0;
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < weights.length; i++) {
            int curWeight = weights[i];
            int count = 0;
            while (!stack.isEmpty() && curWeight > stack.peek()[0]) {
                count = Math.max(count + 1, stack.pop()[1]);
            }
            stack.push(new int[] {curWeight, count});
            res = Math.max(res, count);
        }

        return res;
    }

}
