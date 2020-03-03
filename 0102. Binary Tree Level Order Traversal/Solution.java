/**
 * 
 * Given a binary tree, return the level order traversal of its nodes' values.
 * 
 * Space Complexity: O(n), to store the result 
 * Time Complexity: O(n)
 * 
 */
public class LevelOrder {
    /**
     * 
     * @param root
     * @return level order traversal of tree rooted at root.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // to store the traversal
        List<List<Integer>> result = new LinkedList<>();
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
            List<Integer> nodesLevel = new LinkedList<>();
            for (int i = 0; i < nodesLevelCount; i++) {
                TreeNode current = queue.remove(); // consume current
                nodesLevel.add(current.val);
                // add childrens if exists
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            result.add(nodesLevel);
        }
        return result;
    }
}