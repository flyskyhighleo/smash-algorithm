package queue.stack;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackWithQueue {
    private int topVal;
    private Queue<Integer> queue;

    public ImplementStackWithQueue() {
        this.topVal = -1;
        this.queue = new LinkedList<>();
    }

    public void push(int x) {
        this.queue.offer(x);
        this.topVal = x;
    }

    public int pop() {
        int times = this.queue.size() - 1;
        while (times > 0) {
            if (times == 1) {
                topVal = queue.peek();
            }
            queue.offer(queue.poll());
            times--;
        }
        return queue.poll();
    }

    public int top() {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.topVal;
    }

    public boolean empty() {
        return this.queue.isEmpty();
    }
}
