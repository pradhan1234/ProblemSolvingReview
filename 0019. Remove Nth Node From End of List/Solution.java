/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * Figure explains the approach
 * 
 * Space Complexity: O(1)
 * Time Complexity: O(n)
 * 
 * 
 */
public class Solution {
    /**
     * dummyHead,       n = 2
     *      |
     *      head ---> 1 ---> 2 ---> 3 ---> 4 ---> 5 ---> NULL
     *        |
     *       first
     * 
     *                          
     * dummyHead <- second   first
     *      |                |
     *      head ---> 1 ---> 2 ---> 3 ---> 4 ---> 5 ---> NULL
     * 
     * dummyHead,                 second                first                       
     *      |                       |                     |
     *      head ---> 1 ---> 2 ---> 3 ---> 4 ---> 5 ---> NULL
     *        
     * @param head 
     * @param n 
     * @return head to linked list with Nth node from end, removed.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // base case       
        if(head == null) {
            return null;
        }
        // Always a good approach to use dummy head, makes life easier.
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // Idea is use single pass approach
        ListNode first, second;
        first = head; 
        int count = 0;
        while(count < n) { // increment first, n steps
            first = first.next;
            count++;
        }
        // eventually second should be at the previous of the node that needs to be removed.
        second = dummyHead;
        while(first != null) { 
            first = first.next;
            second = second.next;
        }
        // pointer rearrangement
        second.next = second.next.next;
        return dummyHead.next;
    }
}