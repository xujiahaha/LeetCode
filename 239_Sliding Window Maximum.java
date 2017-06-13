// maxHeap solution
public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length-k+1];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        int start = 0, end = 0, index = 0;
        while(k-- > 0) {
            maxHeap.add(new int[]{nums[end], end});
            end++;
        }
        res[index++] = maxHeap.peek()[0];
        while(end < nums.length) {
            // note this while loop
            // start is the index of the number to be remove from this window
            // maxHeap.peek()[1] is the index of the max number in current window
            // if start < maxHeap.peek()[1], then max number is not changed
            // otherwise, we need to keep polling out
            // eg. [25, 27, 23, 14, -3, 5] k = 3
            // 1st window, max is 27, heap: 27, 25, 23
            // 2nd window, max=27, start=0, maxIndex=1, no need to poll, heap: 27, 25, 23, 14
            // 3rd window, max=23, start=1, maxIndex=1, poll(), maxIndex=0, poll(), maxIndex=3
            // if not keep polling, in third window, maxHeap.peek()[0] will be 25.
            while(!maxHeap.isEmpty() && start >= maxHeap.peek()[1]) maxHeap.poll();
            maxHeap.add(new int[]{nums[end],end});
            res[index++] = maxHeap.peek()[0];
            start++;
            end++;
        }
        return res;
    }
}

