package queue.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/min-stack/description/
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.
 */
public class MinStack {
    private Stack<Integer> data;
    private Stack<Integer> minData;

    public MinStack() {
        this.data = new Stack<>();
        this.minData = new Stack<>();
    }

    public void push(int val) {
        this.data.push(val);
        if (val <= getMin()) {
            this.minData.push(val);
        }
    }

    public int pop() {
        int val = this.data.pop();
        if (val == getMin()) {
            this.minData.pop();
        }
        return val;
    }

    public int top() {
        return this.data.peek();
    }

    public int getMin() {
        if (this.minData.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return this.minData.peek();
    }
}
