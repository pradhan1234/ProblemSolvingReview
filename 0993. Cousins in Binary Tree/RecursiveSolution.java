/**
 * In a binary tree, the root node is at depth 0, and children of each depth k
 * node are at depth k+1. Two nodes of a binary tree are cousins if they have 
 * the same depth, but have different parents.
 * 
 * We are given the root of a binary tree with unique values, and the values x
 * and y of two different nodes in the tree. Return true if and only if the nodes
 * corresponding to the values x and y are cousins.
 * 
 * Idea is to perform traversal of the tree and keep track of depth and parent.
 * When we see either x or y as child of u, we track the depth and its parent.
 * 
 * Space Complexity: O(n)
 * Time Complexity: O(n)
 */
class Solution {
    
    TreeNode parentX = null, parentY = null;
    int levelX = -1, levelY = -1;
    
    public boolean isCousins(TreeNode root, int x, int y) {
        if(root == null) return false;
        
        // root matches X or Y return false;
        if(root.val == x || root.val == y) return false;
        
        dfs(root, x, y, 0);
        
        // parent X and Y, level X and Y
        if(parentX != null && parentY != null && parentX != parentY && levelX == levelY) {
            return true;
        }
        
        return false;
    }
    
    
    // traverses tree
    private void dfs(TreeNode currentNode, int x, int y, int depth) {
        if(currentNode == null) return;
        
        if(currentNode.left != null) {
            if(currentNode.left.val == x) {
                parentX = currentNode;
                levelX = depth;
            } else if(currentNode.left.val == y) {
                parentY = currentNode;
                levelY = depth;
            }
            // call recursively
            dfs(currentNode.left, x, y, depth + 1);
        }
        
        if(currentNode.right != null) {
            if(currentNode.right.val == x) {
                parentX = currentNode;
                levelX = depth;
            } else if(currentNode.right.val == y) {
                parentY = currentNode;
                levelY = depth;
            }
            // call recursively
            dfs(currentNode.right, x, y, depth + 1);
        }
        
    }
}
