public class Solution {
    public void flatten(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        while(cur != null) {
            TreeNode left = cur.left;
            TreeNode right = cur.right;
            if(left != null) {
                // link right to right-most node in left
                TreeNode node = left;
                while(node.right != null) {
                    node = node.right;
                }
                node.right = right;
                // move left to right;
                cur.right = left;
                // set left to null
                cur.left = null;
            }
            // go to next node
            cur = cur.right;
        }
    }
}