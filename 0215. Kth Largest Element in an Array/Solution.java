class Solution {
    public int findKthLargest(int[] nums, int k) {

        // base
        if(nums == null || nums.length == 0) {
            return 0; // ??
        }

        // create minHeap
        // default: natural ordering
        PriorityQueue<Integer> minHeap = new PriorityQueue();

        // add first k elements
        for(int i = 0; i < k; i++) {
            minHeap.add(nums[i]);
        }

        // iterate on rest
        for(int i = k; i < nums.length; i++) {
            // if this is greater then smallest of k elements so far
            // pop and put it in heap
            if(nums[i] > minHeap.peek()) {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }

        return minHeap.peek();

    }
}