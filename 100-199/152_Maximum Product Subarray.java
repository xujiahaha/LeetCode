// O(n) time, O(1) space
public class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return Integer.MIN_VALUE;
        // localMin is the minimum product we can get in a subarray
        // localMax is the maximum product we can get in a subarray 
        int localMin = nums[0], localMax = nums[0], globalMax = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int temp = localMin; 
            // remember to store localMin to temp first, otherwise new localMin will be used when calculating localMax
            // 3 cases to get localMin and localMax, nums[i], nums[i]*localMin, nums[i]*localMax
            localMin = Math.min(nums[i], Math.min(nums[i]*temp, nums[i]*localMax));
            localMax = Math.max(nums[i], Math.max(nums[i]*temp, nums[i]*localMax));
            globalMax = Math.max(globalMax, localMax);
        }
        return globalMax;
    }
    // [1, -3, 9] -> max is [9] -> 9
    // [-1, 3, -9] -> max is [-1, 3, -9] -> 9
}