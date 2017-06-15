// Brute Force: for each position, we have two choices to add sign. 
// O(2^n) time where n is the length of the nums.
// O(n) space since the depth of recursion tree is n
// 743ms
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        
        int[] count = new int[]{0};
        dfs(nums, S, 0, count);
        return count[0];
    }
    
    private void dfs(int[] nums, int s, int idx, int[] count) {
        if(idx == nums.length) {
            if(s == 0) count[0]++;
            return;
        }
        dfs(nums, s+nums[idx], idx+1, count);
        dfs(nums, s-nums[idx], idx+1, count);
    }
}
// In above solution, there are a lot of redundant function calls 
// that are made with same target sum and same idx.
// improve performance by using dfs with memorization
// O(n) space
// time O(l*n) ?
// 129ms
public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
        int[] count = new int[]{0};
        // for each level, store the target sum with number of ways in map. 
        Map[] memo = new HashMap[nums.length];
        for(int i = 0; i < nums.length; i++) {
            memo[i] = new HashMap<Integer, Integer>();
        }
        dfs(nums, S, 0, memo);
        return (int)memo[0].get(S);
    }
    
    private int dfs(int[] nums, int s, int idx, Map[] memo) {
        if(idx == nums.length) {
            if(s == 0) return 1;
            else return 0;
        }
        // if s already exist in current level, directly return the number in map
        if(memo[idx].containsKey(s)) return (int)memo[idx].get(s);
        int plus = dfs(nums, s-nums[idx], idx+1, memo);
        int minus = dfs(nums, s+nums[idx], idx+1, memo);
        memo[idx].put(s, plus+minus);
        return (int)memo[idx].get(s);
    }
}
// To Do: use DP