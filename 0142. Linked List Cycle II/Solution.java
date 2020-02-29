/**
 * Given a linked list, return the node where the cycle begins. If there is no
 * cycle, return null.
 * 
 * To represent a cycle in the given linked list, we use an integer pos which
 * represents the position (0-indexed) in the linked list where tail connects
 * to. If pos is -1, then there is no cycle in the linked list.
 * 
 * Note: Do not modify the linked list.
 * 
 * Idea is to use floyd's cycle detection algorithm, that uses 2 pointers, slow and fast.
 * Once we have detected that cycle exists, we rest one of them at head, and then both increment at speed of 1.
 * When they coincide again, thats the intersecting node.
 * 
 * Space Complexity: O(1)
 * Time Complexity: O(n)
 * 
 */
public class Solution {

    public ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = head;
        slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) { // cycle detected
                slow = head; // reset
                while (slow != fast) { // until convergence
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }

        return null;
    }

}