public class Solution {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        int[] sum = new int[]{0};
        dfs(root, sum, 0);
        return sum[0];
    }
    public void dfs(TreeNode root, int[] sum, int num) {
        if(root == null) return;
        num = num * 10 + root.val;
        // if root is a leaf node, add num to sum[0]
        if(root.left == null && root.right == null) {
            sum[0] += num;
            return;
        }  
        dfs(root.left, sum, num);
        dfs(root.right, sum, num);
    }
}