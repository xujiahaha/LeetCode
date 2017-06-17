public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //for the case of subarray[0,i] == k
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                maxLen = Math.max(maxLen, i - map.get(sum-k));
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}
// if want all the subarry, use Map<Integer, ArrayList> 
// ---> Leetcode 560. Subarray Sum Equals K.
// need to return the total number of subarrays.
public class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) return 0;
        Map<Integer, ArrayList> map = new HashMap<>();
        insert(map, 0, -1);
        int sum = 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                count += map.get(sum-k).size();
            }
            insert(map, sum, i);
        }
        return count;
    }
    
    private void insert(Map<Integer, ArrayList> map, int key, int value) {
        if(!map.containsKey(key)) map.put(key, new ArrayList<Integer>());
        map.get(key).add(value);
    }
}