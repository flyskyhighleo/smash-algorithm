package queue.stack;

public class MyStack {
    private int top;
    private int capacity;
    private int[] data;

    public MyStack(int n) {
        this.top = -1;
        this.capacity = n;
        this.data = new int[n];
    }

    public void push(int value) {
        if (isFull()) {
            return;
        }
        top = top + 1;
        this.data[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int topValue = this.data[top];
        top--;
        return topValue;
    }

    public int peek() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return this.data[top];
    }

    public boolean isEmpty() {
        return this.top < 0;
    }

    public boolean isFull() {
        return this.top == capacity - 1;
    }
}
