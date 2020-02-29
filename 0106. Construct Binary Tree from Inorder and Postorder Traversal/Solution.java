/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * Idea is to use recursion to genearate the binary tree. Explanation in the
 * comments.
 * 
 * Time Complexity: O(n) Space Complexity: O(n), worst case
 * 
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // map to store element --> index mapping
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // call the buildTree helper
        return buildTree(map, preorder, 0, inorder, 0, inorder.length);
    }

    /**
     * 
     * @param map      maps elements of tree to its inorder index
     * @param preorder traversal of binary tree
     * @param p        first index of preorder subarray that we are looking at
     * @param inorder  traversal of binary tree
     * @param i        first index of inorder subarray that we are looking at
     * @param size     size of array, in this call
     * @return
     */
    private TreeNode buildTree(HashMap<Integer, Integer> map, int[] preorder, int p, int[] inorder, int i, int size) {
        // base case check
        if (size <= 0) {
            return null;
        }
        // single element, leaf node
        if (size == 1) {
            return new TreeNode(preorder[p]);
        }
        // obtain root value from the first element in the current preorder array
        // obtain the corresponding inorder index from the map
        // compute left and right size as partitioned wrt root
        int rootValue = preorder[p];
        int rootIndex = map.get(rootValue);
        int leftSize = rootIndex - 1 - i + 1;
        int rightSize = size - leftSize - 1;
        // create a node
        TreeNode root = new TreeNode(rootValue);
        // recursively build left and right subtree
        root.left = buildTree(map, preorder, p + 1, inorder, i, leftSize);
        root.right = buildTree(map, preorder, p + 1 + leftSize, inorder, rootIndex + 1, rightSize);
        return root;
    }
}