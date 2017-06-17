// Solution #1: Sliding Window. O(n) time O(1) space
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int start = 0, end = 0, sum = 0;
        while(end < nums.length) {
            sum += nums[end++];
            if(sum >= s) {
                while(sum >= s) {
                    sum -= nums[start];
                    start++;
                }
                minLen = Math.min(minLen, end - start + 1);
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
// Solution #2: Binary Search. O(nlgn) time O(1) space
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        for(int i = 1; i < nums.length; i++) {
            nums[i] += nums[i-1];
        }
        for(int i = nums.length-1; i >= 0; i--) {
            if(nums[i] >= s) {
                int l = 0, r = i;
                while(l < r) {
                    int mid = l + (r - l) / 2;
                    if(nums[i] - nums[mid] < s) r = mid;
                    else l = mid+1;
                }
                minLen = Math.min(minLen, i-l+1);
            }
        }
        
        
        return minLen == nInteger.MAX_VALUE ? 0 : minLen;
    }
}