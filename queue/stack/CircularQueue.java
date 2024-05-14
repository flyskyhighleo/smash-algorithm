package queue.stack;

/*
https://leetcode.com/problems/design-circular-queue/description/
Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations
are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position
to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue,
once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using
the circular queue, we can use the space to store new values.

Implement the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language.
 */
public class CircularQueue {

    private int capacity;
    private int size;
    private int head;
    private int tail;
    private int[] data;

    public CircularQueue(int k) {
        this.capacity = k;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
        this.data = new int[k];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        data[tail] = value;
        tail += 1;
        if (tail == capacity) {
            tail = 0;
        }
        // tail = tail == capacity - 1 ? 0 : tail + 1;
        size++;
        return true;
    }

    public int deQueue() {
        if (isEmpty()) {
            return -1;
        }
        int first = data[head];
        head += 1;
        if (head == capacity) {
            head = 0;
        }
        // head = head == capacity ? 0 : head + 1;
        size--;
        return first;
    }

    public int front() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    public int rear() {
        if (isEmpty()) {
            return -1;
        }
        return tail == 0 ? data[capacity - 1] : data[tail - 1];
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }
}
