class Solution {
    
    // neighbors
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, 1, 0, -1};
    
    public boolean exist(char[][] board, String word) {
        
        // start point
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        // here?
        return false;
    }
    
    private boolean backtrack(char[][] board, String word, int row, int col, int cursor) {
        
        // success?
        if(cursor == word.length()) {
            return true;
        }

        // iterate on neighbors, iff char matches
        if(isValid(board, row, col) && board[row][col] == word.charAt(cursor)) {
            
            char remember = board[row][col];
            board[row][col] = '$';
            for(int k = 0; k < 4; k++) {    
                int x = row + dx[k];
                int y = col + dy[k];
                
                if(backtrack(board, word, x, y, cursor+1)) {
                    return true;
                }
            
            }
            // restore
            board[row][col] = remember;
            
            
        }
        
        // here?
        return false;
    }
    
    // check bounds
    private boolean isValid(char[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }
}