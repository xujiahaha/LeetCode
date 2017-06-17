public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        dfs(root, res);
        return res;
    }
    
    public int dfs(TreeNode root, List<List<Integer>> res) {
        if(root == null) return 0;
        int height = Math.max(dfs(root.left, res), dfs(root.right, res))+1;
        if(height > res.size()) res.add(new ArrayList<Integer>());
        res.get(height-1).add(root.val);
        return height;
    }
}