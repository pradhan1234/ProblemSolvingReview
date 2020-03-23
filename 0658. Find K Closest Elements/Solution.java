class Solution {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> res=new ArrayList<>();
        int low=0;
        int high=arr.length-k;

        while(low<high){ // crossover

            int mid=low+(high-low)/2;

            if(x - arr[mid] > arr[mid+k]-x) {
                low=mid+1;
            } else{
                high=mid;
            }
        }

        for(int i=0;i<k;i++){
            res.add(arr[low+i]);
        }

        return res;
    }
}
