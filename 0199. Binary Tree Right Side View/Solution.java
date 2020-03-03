/**
 * Given a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * 
 * Space Complexity: O(n) 
 * Time Complexity: O(n)
 */
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        // bfs queue
        Queue<TreeNode> queue = new LinkedList<>();
        // base case check
        if (root == null) {
            return result;
        }
        // init
        queue.add(root);
        while (queue.size() > 0) {
            int nodesLevelCount = queue.size(); // count of nodes in this level
            for (int i = 0; i < nodesLevelCount; i++) {
                TreeNode current = queue.remove(); // consume current
                if (i == nodesLevelCount - 1) { // is this is rightmost node in this level
                    result.add(current.val);
                }
                // add childrens if exists
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return result;
    }
}