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
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                }
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j));
                }
            }
        }

        if (freshOranges == 0) {
            return 0;
        }
        int time = -1;
        int[] dx = new int[] { -1, 0, 1, 0 };
        int[] dy = new int[] { 0, 1, 0, -1 };
        while (!q.isEmpty()) {
            time++;
            int nodesLevel = q.size();
            for (int node = 0; node < nodesLevel; node++) {
                Pair c = q.remove();
                for (int i = 0; i < 4; i++) {
                    int x = c.x + dx[i];
                    int y = c.y + dy[i];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        q.add(new Pair(x, y));
                        freshOranges--;
                    }
                }
            }
        }

        if (freshOranges == 0) {
            return time;
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
