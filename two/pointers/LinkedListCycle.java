package two.pointers;

/*
https://leetcode.com/problems/linked-list-cycle/description/

Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following
the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to.
Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Two pointers, fast and slow.
The fast pointers moves 2 times faster than the slow pointer,
if there's a cycle, the faster pointer must catch the slow pointer in the cycle,
if cycle does not exist, the faster pointer will move out of the range, and they don't meet.

n0 -> n1 -> n2 -> n3
            |     |
            n5 <- n4
 */
public class LinkedListCycle {
    public boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
