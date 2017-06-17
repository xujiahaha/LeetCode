// 339. Nested List Weight Sum
// weight is increasing from root to leaf
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int[] sum = new int[]{0};
        dfs(nestedList, 1, sum);
        return sum[0];
    }
    public void dfs(List<NestedInteger> nestedList, int depth, int[] sum) {
        if(nestedList == null || nestedList.size() == 0) return;
        for(NestedInteger item : nestedList) {
            if(item.isInteger()) {
                sum[0] += item.getInteger()*depth;
            } else {
                dfs(item.getList(), depth+1, sum);
            }
        }
    }
}
// 364. Nested List Weight Sum II
// the weight is defined from bottom up. 
// Idea: get the maxDepth first, then use the same way as I to get sum
public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int[] sum = new int[]{0};
        int depth = getMaxDepth(nestedList);
        dfs(nestedList, depth, sum);
        return sum[0];
    }
    public void dfs(List<NestedInteger> nestedList, int depth, int[] sum) {
        if(nestedList == null || nestedList.size() == 0) return;
        for(NestedInteger item : nestedList) {
            if(item.isInteger()) {
                sum[0] += item.getInteger()*depth;
            } else {
                dfs(item.getList(), depth-1, sum);
            }
        }
    }
    public int getMaxDepth(List<NestedInteger> nestedList) {
        if(nestedList == null || nestedList.size() == 0) return 0;
        int maxDepth = 1;
        for(NestedInteger item : nestedList) {
            if(!item.isInteger()) {
                maxDepth = Math.max(getMaxDepth(item.getList())+1, maxDepth);
            }
        }
        return maxDepth;
    }
}