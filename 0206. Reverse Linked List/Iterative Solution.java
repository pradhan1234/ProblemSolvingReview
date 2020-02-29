class Solution {
    public ListNode reverseList(ListNode head) {
        // base case: LL null or single node.
        if (head == null || head.next == null) {
            return head;
        }
        // Example:
        // head --> 1 --> 2 --> 3 --> 4 --> NULL

        // holds rest of the reversed list.
        ListNode R = reverseList(head.next);
        // head --> 1 ------->-------
        //                          |
        // R --> 4 --> 3 --> 2 --> NULL
        
        // pointer rearrangement:
        head.next.next = head;
        head.next = null;
        // R --> 4 --> 3 --> 2 --> 1 --> NULL
        return R;
    }
}