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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode cur, res = root;
        // if queue is empty, means current level is the bottom level, return res.val
        while(!queue.isEmpty()) {
            int size = queue.size();
            res = queue.peek(); // store left most node on current level
            for(int i = 0; i< size; i++) {
                cur = queue.poll();
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
        }
        return res.val;
    }
}
// DFS
public class Solution {
    public int findBottomLeftValue(TreeNode root) {
        return findBottomLeftValue(root, 1, new int[2]);
    }
    
    public int findBottomLeftValue(TreeNode root, int level, int[] res) {
        if(res[1] < level) {
            res[0] = root.val;
            res[1] = level;
        }
        if(root.left != null) findBottomLeftValue(root.left, level+1, res);
        if(root.right != null) findBottomLeftValue(root.right, level+1, res);
        return res[0];
    }
}