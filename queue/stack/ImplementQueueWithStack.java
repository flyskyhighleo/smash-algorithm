package queue.stack;

import java.util.Stack;

/*
https://leetcode.com/problems/implement-queue-using-stacks/description/
Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the
functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty
operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque
(double-ended queue) as long as you use only a stack's standard operations.
 */
public class ImplementQueueWithStack {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public ImplementQueueWithStack() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void push(int x) {
        this.stack1.push(x);
        if (this.stack2.isEmpty()) {
            moveElements(this.stack1, this.stack2);
        }
    }

    public int pop() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        if (this.stack2.isEmpty()) {
            moveElements(this.stack1, this.stack2);
        }
        return this.stack2.pop();
    }

    public int peek() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        if (this.stack2.isEmpty()) {
            moveElements(this.stack1, this.stack2);
        }
        return this.stack2.peek();
    }

    public boolean isEmpty() {
        return this.stack1.isEmpty() && this.stack2.isEmpty();
    }

    private void moveElements(Stack<Integer> from, Stack<Integer> to) {
        while (!from.isEmpty()) {
            to.push(from.pop());
        }
    }
}
