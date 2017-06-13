/**
 * Time: O(n^2)
 * Space: O(1) without considering the space used for returning result.
*/
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue; // skip the duplicates
            int target = -nums[i];
            int l = i+1, r = nums.length-1;
            while(l < r) {
                int sum = nums[l]+nums[r];
                if(sum == target) {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[l], nums[r])));
                    l++;
                    r--;
                    // skip the duplicates
                    while(l < r && nums[l] == nums[l-1]) l++;
                    while(l < r && nums[r] == nums[r+1]) r--;
                } else if(sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}