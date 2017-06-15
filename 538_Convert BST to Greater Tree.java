/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// DFS, iterative solution, 23ms
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
// recursive version
// 14ms beat 98%
public class Solution {
    public TreeNode convertBST(TreeNode root) {
        dfs(root, new int[]{0});
        return root;
    }
    public void dfs(TreeNode root, int[] sum) {
        if(root == null) return;
        dfs(root.right, sum);
        sum[0] += root.val;
        root.val = sum[0];
        dfs(root.left, sum);
    }
}