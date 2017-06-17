public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // Since the size of subarray is at least 2.
        if (nums.length <= 1) return false;
        // Two continuous "0" will form a subarray which has sum = 0. 0 * k == 0 will always be true. 
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) return true;
        }
        
        // At this point, k can't be "0" any longer.
        if (k == 0) return false;
        // Let's only check positive k. Because if there is a n makes n * k = sum, it is always true -n * -k = sum.
        if (k < 0) k = -k;
        
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        set.add(0);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            
            if (i > 0) {
                // Validate from the biggest possible n * k to k
                for (int j = (sum / k) * k; j >= k; j -= k) {
                    if (set.contains(sum - j)) return true;
                }
            }
            
            set.add(sum);
        }
        
        return false;
    }
}