public class Solution {
    public int removeDuplicates(int[] nums) {
        // special case
        if(nums == null) return 0;
        if(nums.length < 2) return nums.length;
        
        // use two pointers. 
        // i is used to track the first occurrence of a number
        // j is used to scan the following number to skip duplicates
        int i = 0, j = 1;
        while(i < nums.length) {
            while(j < nums.length && nums[j] == nums[i]) j++;
            // if the following numbers are all duplicates of nums[i], break and return i+1
            if(j == nums.length) break;
            // if find a different number, copy that to nums[i+1]
            nums[++i] = nums[j];
            j++;
        }
        return i+1;
    }
}