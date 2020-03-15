/**
 * In a given grid, each cell can have one of three values:
 * 
 * 1. the value 0 representing an empty cell; the value 1 representing a fresh
 * 2. orange; the value 2 representing a rotten orange. Every minute, any fresh
 * 3. orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange. If this is impossible, return -1 instead.
 * 
 * Idea:
 * Use breadth first search from the rotten oranges, track time until termination.
 * 
 * Space Complexity: O(mn)
 * Time Complexity: O(mn)
 *  
 */
class Solution {
    public int orangesRotting(int[][] grid) {
        // base checks
        if(grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        // bfs
        Queue<Pair> q = new LinkedList<>(); // contains rotten oranges
        int freshOranges = 0;
        
        // compute freshOranges, rotten to queue
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    freshOranges++;
                } 
                if(grid[i][j] == 2) {
                    q.add(new Pair(i, j)); // add rotten
                }
            }
        }
        
        if(freshOranges == 0) {
            return 0;
        }
        
        // start process rot oranges
        int time = 0; // tick
        int[] dx = new int[]{-1, 0, 1, 0};
        int[] dy = new int[]{0, 1, 0, -1};
        
        while(!q.isEmpty()) {
            time++;
            int countRottenOrangesLevel = q.size();
            for(int i = 0; i < countRottenOrangesLevel; i++) {
                Pair cell = q.poll();
                for(int k = 0; k < 4; k++) {
                    int x = cell.x + dx[k];
                    int y = cell.y + dy[k];
                    
                    if(x >= 0 && x < m && y >=0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        freshOranges--;
                        
                        if(freshOranges == 0) return time;
                        q.add(new Pair(x, y));
                    }
                }
            }
        }
        
        return -1;
    }
    
}

class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
