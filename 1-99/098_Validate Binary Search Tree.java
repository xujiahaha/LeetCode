/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        // corner case
        if(root == null) return true;
        
        // all node.val in left subtree must be in the range of (min, root.val), exclusive
        // all node..val in right subtree must be in the range of (root.val, max), exclusive
        return isValidBST(root.left, Long.MIN_VALUE, root.val) && isValidBST(root.right, root.val, Long.MAX_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
// we nned to declare min and max as long type
// if use int, the range for left and right subtree would be [min, root.val) and (root.val, max]
// it is not consistent. This way, we need to consider left and right as two separate function.
// If we use long type, the rang will be (min, root.val) and (root.val, max). consistent.