/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Time: O(n) since we need to visit all nodes 
// Space: O(n) worst case when the tree is complete tree. Bottom level contains n/2 nodes.
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur;
        while(!queue.isEmpty()) {
            int size = queue.size();
            res.add(queue.peek().val); // first node on current level is the right most one
            for(int i = 0; i < size; i++) {
                cur = queue.poll();
                // add right child first to make right most node is the first node on each level
                if(cur.right != null) queue.add(cur.right);
                if(cur.left != null) queue.add(cur.left);
            }
        }
        return res;
    }
}