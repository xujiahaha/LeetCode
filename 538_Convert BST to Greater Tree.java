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
    public TreeNode convertBST(TreeNode root) {
        // corner case: root == null. then return root.
        if(root == null) return root;
        
        // starting from right most node, which is the largest in the tree. 
        // right -> root -> left
        Stack<TreeNode> stack = new Stack<>();
        int pre = 0;
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.right;
            }
            cur = stack.pop();
            cur.val += pre;
            pre = cur.val;
            cur = cur.left;
        }
        return root;
    }
}