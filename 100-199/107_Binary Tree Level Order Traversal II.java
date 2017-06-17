/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Time: O(n)
// Space: O(n) worst case. 
// when the tree is a complete tree, queue needs to store the bottom level with n/2 nodes.
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur;
        while(!queue.isEmpty()) {
            int k = queue.size();
            // traverse current level with size of k nodes
            List<Integer> sublist = new ArrayList<>();
            for(int i = 0; i < k; i++) {
                cur = queue.poll();
                sublist.add(cur.val);
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
            // add current level at the front
            res.add(0, sublist);
        }
        return res;
    }
}