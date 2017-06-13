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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) return null;
        return buildBST(inorder, 0, inorder.length-1, postorder, postorder.length-1);
    }
    public TreeNode buildBST(int[] inorder, int iStart, int iEnd, int[] postorder, int pEnd) {
        if(iStart > iEnd || pEnd < 0) return null;
        int value = postorder[pEnd];
        TreeNode root = new TreeNode(value);
        int index = iStart;
        while(index <= iEnd) {
            if(inorder[index] == value) break;
            index++;
        }
        // number of nodes in left is index-iStart
        // number of nodes in right subtree is iEnd-(index+1)+1 = iEnd-index
        root.left = buildBST(inorder, iStart, index-1, postorder, pEnd-iEnd+index-1);
        root.right = buildBST(inorder, index+1, iEnd, postorder, pEnd-1);
        return root;
    }
}