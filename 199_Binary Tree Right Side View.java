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
// DFS solution.
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 1, res);
        return res;
    }
    
    public void dfs(TreeNode root, int level, List<Integer> res) {
        if(root == null) return;
        // if res.size() < level, meaning this is the right most node on this level
        if(res.size() < level) res.add(root.val);
        dfs(root.right, level+1, res);
        dfs(root.left, level+1, res);
    }
}