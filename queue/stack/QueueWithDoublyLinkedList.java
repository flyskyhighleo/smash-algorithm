package queue.stack;

public class QueueWithDoublyLinkedList {

    private int size;
    private Node head;
    private Node tail;

    public QueueWithDoublyLinkedList() {
        this.size = 0;
        this.head = new Node(-1);
        this.tail = new Node(-1);
    }

    public void addFirst(Node node) {
        Node first = this.head.next;
        node.prev = this.head;
        this.head.next = node;
        node.next = first;
        first.prev = node;
    }

    public void addLast(Node node) {
        Node last = this.tail.prev;
        node.prev = last;
        last.next = node;
        node.next = this.tail;
        this.tail.prev = node;
    }

    public Node removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = this.head.next;
        remove(first);
        return first;
    }

    public Node removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = this.tail.prev;
        remove(last);
        return last;
    }

    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    class Node {
        int val;
        Node prev;
        Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
}
