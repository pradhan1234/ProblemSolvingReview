/**
 * An image is represented by a 2-D array of integers, each integer representing
 * the pixel value of the image (from 0 to 65535).
 * 
 * Given a coordinate (sr, sc) representing the starting pixel (row and column)
 * of the flood fill, and a pixel value newColor, "flood fill" the image.
 * 
 * To perform a "flood fill", consider the starting pixel, plus any pixels
 * connected 4-directionally to the starting pixel of the same color as the
 * starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color as the starting pixel), and so on. Replace the
 * color of all of the aforementioned pixels with the newColor.
 * 
 * At the end, return the modified image.
 * 
 * Idea:
 * Use breadth first search on the given location, start off by marking the positions to be updated as -1,
 * Then in breadth first manner, try visiting the 4 neighbouts and continue until termination.
 * 
 * Space Complexity: O(mn)
 * Time Complexity: O(mn)
 * 
 */
 
 class Solution {
    
    int[] dx = new int[]{-1, 0, 1, 0};
    int[] dy = new int[]{0, 1, 0, -1};
        
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        
        // base case checks
        if(image == null) return image;
        int m = image.length;
        if(m == 0) return image;
        int n = image[0].length;
        
        // newColor == color at start pixel
        if(newColor == image[sr][sc]) return image;
        
        dfsVisit(image, image[sr][sc], sr, sc, newColor);
        
        return image;
    }
    
    private void dfsVisit(int[][] image, int srcColor, int row, int col, int newColor) {
        // visit, color
        image[row][col] = newColor;
        
        // iterate neighbors
        //      visit them?
        for(int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            
            if(isValid(image, x, y) && image[x][y] == srcColor) {
                dfsVisit(image, srcColor, x, y, newColor);
            }
            
        }
        
    }
    
    private boolean isValid(int[][] image, int row, int col) {
        // bounds check
        return row >= 0 && row < image.length && col >= 0 && col < image[0].length;
    }
}
