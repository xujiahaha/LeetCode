/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Approach #1: preorder traversal (no need "#" to mark null)
// Approach #2: level order taversal (work similar on a BT, but nothing to do with BST feature)
public class Codec {

    private String spliter = ",";
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        // preorder traversal
        preorderTraverse(root, sb);
        return sb.toString();
    }

    private void preorderTraverse(TreeNode root, StringBuilder sb) {
        if(root == null) return;
        sb.append(root.val).append(spliter);
        preorderTraverse(root.left, sb);
        preorderTraverse(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        String[] vals = data.split(spliter);
        return buildBST(vals, 0, vals.length-1);
    }
    
    private TreeNode buildBST(String[] data, int start, int end) {
        if(start > end) return null;
        int val = Integer.valueOf(data[start]);
        TreeNode root = new TreeNode(val);
        int i = start + 1;
        // all numbers that are smaller than the value of root should be on left subtree
        while(i <= end && Integer.valueOf(data[i]) < val) i++;
        root.left = buildBST(data, start+1, i-1);
        root.right = buildBST(data, i, end);
        return root;
    }
}
