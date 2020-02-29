/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 * 
 * Idea is as follows:
 * 1. get to the middle of the linked list
 * 2. reverse the later half of the linked list
 * 3. now we have the first half, and the reversed second half, start interleaving them.
 * 
 * Space Complexity: O(1)
 * Time Complexity: O(n)
 * 
 */
public class Solution {
    /**
     * 
     * @param head 
     */
    public void reorderList(ListNode head) {
        if(head == null) return;
        // get to middle of the linked list
        ListNode mid = partition(head);
        // reverse the later half.
        ListNode R = reverse(mid.next);
        mid.next = null; // break link joining these 2 halves.

        ListNode cursorA, cursorB;
        ListNode nextA, nextB;

        cursorA = head;
        cursorB = R;
                
        // pointer rearrangement, we are now interleaving the nodes.        
        while(cursorB != null) { 
            nextA = cursorA.next;
            nextB = cursorB.next;
            
            cursorA.next = cursorB;
            cursorB.next = nextA;
            
            cursorA = nextA;
            cursorB = nextB;
        }
    }
    
    /**
     * 
     * head --> 1 --> 2 --> 3 --> 4 --> 5 --> NULL
     *                    slow          fast
     * 
     * head --> 1 --> 2 --> 3 --> 4 --> 5 --> 6 --> NULL
     *                    slow         fast
     * 
     * Terminate: When fast at last or second last, as we have slow at mid.
     * 
     * @param head
     * @return node at which the second half of linked list starts.
     */
    private ListNode partition(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    /**
     * 
     * @param head
     * @return head to reversed linkedlist
     */
    private ListNode reverse(ListNode head) {
        ListNode reverseHead = null;
        ListNode cursor = head;
        ListNode next;
        while(cursor != null) {
            next = cursor.next;
            cursor.next = reverseHead;
            reverseHead = cursor;
            cursor = next;
        }
        return reverseHead;
    }
}