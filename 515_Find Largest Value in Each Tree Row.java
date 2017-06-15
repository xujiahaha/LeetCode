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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur;
        while(!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                cur = queue.poll();
                max = Math.max(max, cur.val);
                if(cur.left != null) {
                    queue.add(cur.left);
                }
                if(cur.right != null) {
                    queue.add(cur.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}
// dfs solution
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        dfs(root, 1, res);
        return res;
    }
    public void dfs(TreeNode root, int level, List<Integer> res) {
        if(root == null) return;
        if(level > res.size()) res.add(root.val);
        if(root.val > res.get(level-1)) res.set(level-1, root.val);
        dfs(root.left, level+1, res);
        dfs(root.right, level+1, res);
    }
}