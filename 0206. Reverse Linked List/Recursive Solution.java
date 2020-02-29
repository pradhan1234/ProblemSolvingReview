class Solution {
    /**
     * Figure:
     * 
     * head ---> 1 ---> 2 ---> 3 ---> 4 ---> NULL
     * 
     * initialization: NULL <--- R         cursor ---> head
     * 
     * somewhere during the iteration: Loop Invariant
     * 
     * NULL <--- 1 <--- 2 <--- R           cursor ---> 3 ---> 4 ---> NULL 
     *                                              next -----^ 
     * 
     * Termination: 
     * NULL <--- 1 <--- 2 <--- 3 <--- 4 <--- R
     * 
     * @param head
     * @return head to reversed linkedlist
     */
    public ListNode reverseList(ListNode head) {
        ListNode R, cursor, next;
        R = null;
        cursor = head;

        while (cursor != null) {
            // pointer rearrangement
            next = cursor.next;
            cursor.next = R;
            R = cursor;
            cursor = next;
        }

        return R;
    }
}