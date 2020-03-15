class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // solution
        List<List<Integer>> solution = new LinkedList<>();
        // state
        List<Integer> state = new LinkedList<>();
        backtrack(solution, target, state, 0, 0, candidates);
        return solution;
    }
    
    private void backtrack(List<List<Integer>> solution, int target, List<Integer> state, int sum, int index, int[] candidates) {
        // isValid
        if(sum > target) {
            return;
        }
        // isGoal
        if(sum == target) {
            solution.add(new LinkedList<>(state));
        }
        // try candidates
        for(int i = index; i < candidates.length; i++) {
            state.add(candidates[i]);
            backtrack(solution, target, state, sum + candidates[i], i, candidates);
            state.remove(state.size() - 1);
        }
    }
}