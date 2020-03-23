/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Custom Comparator
        Comparator customComparator = new Comparator<ListNode>() {
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val; // natural ordering
            }};

        // Create PQ with CustomComparator
        PriorityQueue<ListNode> pq = new PriorityQueue(customComparator);

        // Add all head nodes
        for(ListNode headNode: lists) {
            if(headNode != null) {
                pq.add(headNode);
            }
        }

        // Result
        ListNode result = new ListNode(0);
        ListNode cursor = result;

        while(!pq.isEmpty()) {
            ListNode minNode = pq.remove();
            cursor.next = minNode;
            cursor = cursor.next;
            // insert next of minNode if exist
            if(minNode.next != null) {
                pq.add(minNode.next);
            }
        }

        return result.next;
    }
}