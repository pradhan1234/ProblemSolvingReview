class Solution {
    public int hIndex(int[] citations) {
        // base
        if(citations == null || citations.length == 0) {
            return 0;
        }

        int n = citations.length;
        int low = 0;
        int high = n - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;

            int citation = citations[mid];
            int count = n - mid;

            // found
            if(citation == count) return count;

            if(citation < count) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return n - low;
    }
}