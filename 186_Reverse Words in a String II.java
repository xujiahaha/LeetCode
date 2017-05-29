 // Time complexity: O(n), space complexity is O(1)
public class Solution {
    public void reverseWords(char[] s) {
        // check corner case
        if(s == null || s.length <= 1) return;
        
        // 1. reverse word by word. eg. "the sky is blue" will be reversed to "eht yks si eulb"
        int start = 0, end = 0;
        while(end < s.length) {
            // find the end index of one word
            while(end < s.length && s[end] != ' ') end++;
            reverse(s, start, end-1);
            end = end + 1;
            start = end;
        }
        // 2. reverse whole array. eg. "eht yks si eulb" will be reversed to "blue is sky the"
        reverse(s, 0, s.length-1);
    }
    public void reverse(char[] s, int start, int end) {
        if(start < 0 || end >= s.length) return;
        while(start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }
}