package queue.stack;

public class MyCircularDeque {
    private int capacity;
    private int size;
    private int left;
    private int right;
    private int[] data;

    public MyCircularDeque(int k) {
        this.capacity = k;
        this.size = 0;
        this.left = 0;
        this.right = 0;
        this.data = new int[k];
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        left = left == 0 ? capacity - 1 : left - 1;
        data[left] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        data[right] = value;
        size++;
        right = right == capacity - 1 ? 0 : right + 1;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        left = left == capacity - 1 ? 0 : left + 1;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        right = right == 0 ? capacity - 1 : right - 1;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : data[left];
    }

    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return right == 0 ? data[capacity - 1] : data[right - 1];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean isFull() {
        return this.size == this.capacity;
    }
}
