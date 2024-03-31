package two.pointers;

/*
https://leetcode.com/problems/middle-of-the-linked-list/

Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

fast and slow pointers.
fast pointers moves 2 times faster than the slow pointer.
when the fast pointer reaches the last element or out of range, the slow pointers
stays at the middle node.

n0 -> n1 -> n2 -> n3 -> n4
s     s      s
f            f          f

n0 -> n1 -> n2 -> n3
s     s      s
f            f        f
*/
public class MiddleOfLinkedList {
    public Node middleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
