// Solution #1
// O(n^2) time, when the input array is in descending order
// O(1) space
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int res = 0, start = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                int j = i;
                while (j > 0 && nums[j] < nums[j-1]) {
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                    j--;
                }
                if (start == -1 || start > j) start = j;
                res = Math.max(res, i - start + 1);
            }
        }
        return res;
    }
}
// Solution #2: sort
// O(nlgn) time for sorting
// O(n) space to store a copy of the array
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int len = 0;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == sorted[i]) len++;
            else break;
        }
        for (int j = nums.length-1; j >=0; j--){
            if (nums[j] == sorted[j]) len++;
            else break;
        }
        return Math.max(0, nums.length-len);
    }
}
// Solution #3
// O(n) time