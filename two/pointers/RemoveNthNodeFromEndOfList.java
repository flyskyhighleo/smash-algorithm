package two.pointers;

/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
Given the head of a linked list, remove the nth node from the end of the list and return its head.
========================================================================================================================
two pointers, slow and fast.
move fast n + 1 steps
then move both slow and fast until fast reaches null
slow is where the prev node of the target
d -> n0 -> n1 -> n2 -> n3 -> n4
s    f
NOTE: dummy node is frequently used in linked list to avoid some null pointers.
 */
public class RemoveNthNodeFromEndOfList {

    public Node removeNthNodeFromEnd(Node head, int n) {
        Node dummy = new Node();
        dummy.next = head;
        Node slow = dummy;
        Node fast = dummy;

        while (n >= 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        Node target = slow.next;
        slow.next = target.next;

        return dummy.next;
    }


}
