/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other. Given an integer n, return all
 * distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 */
class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> placement = new ArrayList<>(); // to store state

        backtrack(result, placement, 0, n);
        return generateBoards(result, n);
    }

    /**
     * Convert the placement to board representation.
     * @param result 
     * @param n
     * @return board from representation of goal state
     */
    private List<List<String>> generateBoards(List<List<Integer>> result, int n) {
        List<List<String>> boards = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        for (List<Integer> placement : result) {
            List<String> board = new LinkedList<>();
            for (int col : placement) {
                sb.replace(col, col + 1, "Q");
                board.add(sb.toString());
                sb.replace(col, col + 1, ".");
            }
            boards.add(board);
        }
        return boards;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> placement, int row, int n) {
        if (row == n) { // add to results
            result.add(new ArrayList<>(placement));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (safeVertical(placement, row, j, n) && safeDiagonal(placement, row, j, n)) { // place row'th queen on col j iff feasible
                placement.add(j);
                backtrack(result, placement, row + 1, n);
                placement.remove(placement.size() - 1);
            }
        }
    }

    /**
     * 
     * @param placement
     * @param row
     * @param col
     * @param n
     * @return true if it is safe, ie no prev queen has been placed in vertical of this position.
     */
    private boolean safeVertical(List<Integer> placement, int row, int col, int n) {
        int i = row - 1;
        while (i >= 0) {
            if (placement.get(i) == col) {
                return false;
            }
            i--;
        }
        return true;
    }

    /**
     * 
     * @param placement
     * @param row
     * @param col
     * @param n
     * @return true if it is safe, ie no prev queen has been placed in diagonal primary and secondary of this position.
     */
    private boolean safeDiagonal(List<Integer> placement, int row, int col, int n) {
        // primary
        int i = row - 1, j = col - 1;
        while (i >= 0 && j >= 0) {
            if (placement.get(i) == j) {
                return false;
            }
            i--;
            j--;
        }
        // secondary
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < n) {
            if (placement.get(i) == j) {
                return false;
            }
            i--;
            j++;
        }

        return true;
    }
}