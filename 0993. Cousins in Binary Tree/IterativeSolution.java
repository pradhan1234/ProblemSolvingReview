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
public class Cousins {
    public boolean isCousins(TreeNode root, int x, int y) {
        // base cases
        if (root == null) {
            return false;
        }

        TreeNode parentX = null, parentY = null;
        int depthX = -1, depthY = -1;
        int depth = 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int countNodesLevel = q.size();
            depth++; // increment
            for (int v = 0; v < countNodesLevel; v++) {
                TreeNode u = q.remove();
                if (u.left != null) {
                    if (u.left.val == x) {
                        parentX = u;
                        depthX = depth;
                    } else if (u.left.val == y) {
                        parentY = u;
                        depthY = depth;
                    }
                    q.add(u.left);
                }
                if (u.right != null) {
                    if (u.right.val == x) {
                        parentX = u;
                        depthX = depth;
                    } else if (u.right.val == y) {
                        parentY = u;
                        depthY = depth;
                    }
                    q.add(u.right);
                }
            }
        }
        // parent should be set now, and should be distinct, also depth should be same.
        if (parentX != null && parentY != null && (parentX.val != parentY.val) && depthX == depthY) {
            return true;
        }
        return false;
    }
}
