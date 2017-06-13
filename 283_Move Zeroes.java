public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length <= 1) return;
        // two pointers. i track first avaliable position. j scan the array to find nun-zero number
        int i = 0, j = 0;
        while(i < nums.length && j < nums.length) {
            // for case like [1,2,3,4,5,6], 重复赋值
            if(nums[j] != 0) { 
                // 没有重复赋值，但多了一次判断。
                // if(j != i) nums[i] = nums[j];
                // i++;
                // j++;
                nums[i] = nums[j];
                i++;
                j++;
            }else {
                j++;
            }
        }
        while(i < nums.length) {
            nums[i++] = 0;
        }
    }
}