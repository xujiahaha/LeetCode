// 112. path sum---------------------------------------------------------------------------
// root-to-leaf path. returb boolean
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.val == sum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}
// 113. path sum II------------------------------------------------------------------------
// root-to-leaf path. return list of path.
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null) return res;
        getPath(root, sum, res, new ArrayList<Integer>());
        return res;
    }
    public void getPath(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
        if(root == null) return;
        path.add(root.val);
        // find a root-to-leaf path
        if(root.val == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<Integer>(path));
        }else{
            getPath(root.left, sum-root.val, res, path);
            getPath(root.right, sum-root.val, res, path);
        }
        path.remove(path.size()-1);
    }
}
// 437. path sum III------------------------------------------------------------------------
// path can start or end at any node, but must be downwards. return number of paths.
// Solution #1 - easy to understand
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        // find all possible path starting from any node
        return getPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    public int getPath(TreeNode root, int sum) {
        int count = 0;
        if(root == null) return count;
        if(root.val == sum) count = 1;
        count += getPath(root.left, sum-root.val);
        count += getPath(root.right, sum-root.val);
        return count;
    }
}
// Solution #2 - 用HashMap存之前见过的sum。重点理解。
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        // map sum met before to its occurrence
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        int[] count = new int[1];
        findPath(root, sum, 0, preSum, count);
        return count[0];
    } 

    public void findPath(TreeNode root, int sum, int curSum, Map<Integer, Integer> preSum, int[] count) {
        if(root == null) return;
        curSum += root.val;
        // once there is curSum - preSum == sum, we find one path
        if(preSum.containsKey(curSum - sum)){ 
            count[0] += preSum.get(curSum - sum);
        }
        // add curSum to map
        preSum.put(curSum, preSum.getOrDefault(curSum, 0)+1);
        findPath(root.left, sum, curSum, preSum, count);
        findPath(root.right, sum, curSum, preSum, count);
        preSum.put(curSum, preSum.get(curSum)-1);
    }
}
// 124. Binary Tree Maximum Path Sum------------------------------------------------------------------------
// starting from any node and end at any node
public class Solution {
    public int maxPathSum(TreeNode root) {
        int[] max = new int[]{Integer.MIN_VALUE}; // note the initial value is MIN
        getMaxPathSum(root, max);
        return max[0];
    }
    
    public int getMaxPathSum(TreeNode root, int[] max) {
        if(root == null) return 0;
        int left = getMaxPathSum(root.left, max);
        int right = getMaxPathSum(root.right, max);
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        max[0] = Math.max(max[0], root.val+left+right);
        return Math.max(left, right)+root.val; // return the max sum we can get containing root
    }
}
