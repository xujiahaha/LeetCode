/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Time: O(n) where n is the number of nodes in the tree
// Space: O(n) worst case when the tree is skew to a linked list
public class Solution {
    private int maxFreq = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
        HashMap<Integer, Integer> map = new HashMap<>(); // map sum to freq
        getTreeSum(root, map);
        if(maxFreq == 0) return new int[0];
        // traverse the whole map, add all sum with frequency of maxFreq to list
        Iterator<Integer> itr = map.keySet().iterator();
        List<Integer> list = new ArrayList<>();
        while(itr.hasNext()) {
            int sum = itr.next();
            int freq = map.get(sum);
            if(freq == maxFreq) list.add(sum);
        }
        // convert list to int array
        int[] res = new int[list.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    public int getTreeSum(TreeNode root, HashMap<Integer, Integer> map) {
        if(root == null) return 0; // base case
        int left = getTreeSum(root.left, map);
        int right = getTreeSum(root.right, map);
        int sum = left + right + root.val;
        int freq = map.getOrDefault(sum, 0)+1;
        map.put(sum, freq);
        // update the max frequency
        maxFreq = Math.max(maxFreq, freq);
        return sum;
    }
}