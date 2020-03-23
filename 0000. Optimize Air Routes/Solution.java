import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
​
public class Solution {
    public List<int[]> optimize(int[][] forward, int[][] backward, int target) {
​
        List<int[]> result = new ArrayList<>();
        if (forward == null || forward.length == 0)
            return result;
​
        int max = 0;
​
        Arrays.sort(backward, (a, b) -> a[1] - b[1]);
​
        for (int i = 0; i < forward.length; i++) {
​
            int complement = target - forward[i][1];
            int index = binarySearch(backward, complement);
            if (index != -1) {
                int sum = forward[i][1] + backward[index][1];
                if (sum >= max) {
                    if (sum > max) {
                        result = new ArrayList<>();
                    }
                    max = Math.max(max, sum);
                    result.add(new int[] { forward[i][0], backward[index][0] });
                }
            }
        }
        return result;
    }
​
    private int binarySearch(int[][] arrays, int target) {
        int low = 0;
        int high = arrays.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arrays[mid][1] == target)
                return mid; // found
            else if (arrays[mid][1] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // when binary search terminates in these cases, high holds desired value
        return high;
    }
​
    public static void main(String[] args) {
​
        Solution solution = new Solution();
        int[][] forward, backward;
        int target;
        List<int[]> resultList;
​
        // test case 1
        forward = new int[][] {{1, 4000}, {2, 2000}, {3, 6000}};
        backward = new int[][] {{1, 4000}, {2, 2500}, {3, 2800}};
        target = 7000;
​
        resultList = solution.optimize(forward, backward, target);
        System.out.println("Test Case 1: \nExpect: [1, 3]");
​
        System.out.print(" Output: ");
        for (int[] result : resultList) {
            System.out.print("[" + result[0] + ", " + result[1] +"], ");
        }
        System.out.println();
​
        // test case 2
        forward = new int[][] {{1, 4000}, {2, 2000}, {3, 6000}};
        backward = new int[][] {{1, 4000}, {2, 2000}, {3, 2800}};
        target = 6000;
​
        resultList = solution.optimize(forward, backward, target);
        System.out.println("Test Case 2: \nExpect: [1, 2], [2, 1]");
​
        System.out.print(" Output: ");
        for (int[] result : resultList) {
            System.out.print("[" + result[0] + ", " + result[1] +"], ");
        }
        System.out.println();
​
​
​
​
        // test case 3
        forward = new int[][] {{1, 3000}, {2, 7000}, {3, 5000}, {4, 10000}};
        backward = new int[][] {{1, 2000}, {2, 5000}, {3, 4000}, {4,3000}};
        target = 10000;
​
        resultList = solution.optimize(forward, backward, target);
        System.out.println("Test Case 3: \nExpect: [2, 4], [3, 2]");
​
        System.out.print(" Output: ");
        for (int[] result : resultList) {
            System.out.print("[" + result[0] + ", " + result[1] +"], ");
        }
        System.out.println();
​
​
    }
}