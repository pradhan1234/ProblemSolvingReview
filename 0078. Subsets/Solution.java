/**
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Idea: Use backtracking to generate all the subsets of the given set.
 * 
 * Space Complexity: O(2^n) 
 * Time ComplexitY: O(2^n)
 * 
 */
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> state = new LinkedList<>();
        backtrack(result, state, nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> state, int[] nums, int cursor) {
        // add current state to result
        result.add(new LinkedList<>(state));
        // recurse by choosing/skipping the element
        for (int i = cursor; i < nums.length; i++) {
            state.add(nums[i]); // add candidate
            backtrack(result, state, nums, i + 1);
            state.remove(state.size() - 1); // remove
        }
    }
}