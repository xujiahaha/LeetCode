public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        return buildTree(preorder, inorder, 0, 0, inorder.length-1);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder, int pStart, int iStart, int iEnd) {
        if(pStart >= preorder.length || iStart > iEnd) return null;
        int value = preorder[pStart];
        TreeNode root = new TreeNode(value);
        int index = iStart;
        while(index < iEnd) {
            if(inorder[index] == value) break;
            index++;
        }
        root.left = buildTree(preorder, inorder, pStart+1, iStart, index-1);
        // number of nodes in left subtree is index-iStart.
        root.right = buildTree(preorder, inorder, pStart+index-iStart+1, index+1, iEnd);
        return root;
    }
} 