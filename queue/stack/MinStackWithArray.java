package queue.stack;

public class MinStackWithArray {
    private static final int N = 30000;
    private int dataTop;
    private int minTop;
    private int[] data;
    private int[] minData;

    public MinStackWithArray() {
        this.dataTop = 0;
        this.minTop = 0;
        this.data = new int[N];
        this.data = new int[N];
    }

    public void push(int val) {
        if (dataTop <= N - 1) {
            this.data[dataTop++] = val;
            if (minTop <= 0 || val <= this.minData[minTop - 1]) {
                this.minData[minTop++] = val;
            }
        }
    }

    public int pop() {
        if (dataTop <= 0) {
            return Integer.MIN_VALUE;
        }
        int topVal = this.data[dataTop - 1];
        dataTop--;
        if (topVal == getMin()) {
            minTop--;
        }
        return topVal;
    }

    public int top() {
        if (dataTop <= 0) {
            return Integer.MIN_VALUE;
        }
        return this.data[dataTop - 1];
    }

    public int getMin() {
        if (minTop <= 0) {
            return Integer.MIN_VALUE;
        }
        return this.minData[minTop - 1];
    }
}
