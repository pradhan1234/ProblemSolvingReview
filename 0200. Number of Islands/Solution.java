class Solution {

    int[] dx = new int[] { -1, 0, 1, 0 };
    int[] dy = new int[] { 0, 1, 0, -1 };

    public int numIslands(char[][] grid) {
        // base cases
        if(grid == null || grid.length == 0) return 0;
        // dimensions of matrix
        int m = grid.length, n = grid[0].length;
        
        // DFS approach, each call to DFS-VISIT covers one Island.
        // iterate matrix, if island not visited visit?
        int count = 0;
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfsVisit(grid, m, n, i, j);
                }
            }
        }
        
        return count;
    }

    private void dfsVisit(char[][] grid, int m, int n, int row, int col) {
        // visit: mark visited '2'
        grid[row][col] = '2';
        // let's try to visit its 4 neighbors, maybe?
        for(int i = 0; i < 4; i++) {
            
            int x = row + dx[i];
            int y = col + dy[i];
            
            if(isValid(grid, m, n, x, y) && grid[x][y] == '1') {
                dfsVisit(grid, m, n, x, y);
            }
        }
    }
    
    private boolean isValid(char[][] grid, int m, int n, int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}