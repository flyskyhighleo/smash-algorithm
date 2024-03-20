package two.pointers.fast_and_slow;

/*
https://leetcode.com/problems/linked-list-cycle-ii/description/
Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following
the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to
(0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
Do not modify the linked list.

To detect if a cycle exit in a linked list, we can use two pointers (fast and slow pointers).
If cycle exist, fast and slow must meet in the cycle, otherwise, they will never meet. (the fast pointer will move out
of the linked list)

Set the meeting point as M
Set the start point of cycle is S
Set the start of linked list is H
Set the circumference of cycle is C

When the two pointers meet,

S_fast - S_slow = k * C. To simplify, k = 1
S_fast - S_slow = C
S_fast = 2 * S_slow
S_slow = C

S_slow = HS + SM
S_slow = HS+SM = C = SM + MS
==> HS = MS

Therefore, to find the start of the cycle, when the two pointers meet,
set the fast pointer back to "head"
Make faster and slow pointers move with speed of one step until they meet

The meeting point is the start of the cycle.
 */
public class LinkedListCycleII {

    public Node findStartOfCycle(Node head) {
        Node slow = head;
        Node fast  = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            return null;
        }

        fast = head;

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

}
