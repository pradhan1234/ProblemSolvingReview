class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode nodeA, TreeNode nodeB) {
        if(nodeA == null && nodeB == null) {
            return true;
        }
        if(nodeA == null || nodeB == null || nodeA.val != nodeB.val) {
            return false;
        }
        
        return isSymmetric(nodeA.left, nodeB.right) && isSymmetric(nodeA.right, nodeB.left);
    }
}