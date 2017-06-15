// DFS. 4ms. beat 96%
// first version code. need further refactor
public class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return Integer.MAX_VALUE;
        int[] idx = new int[]{0};
        return calculate(s, idx);
    }
    public int calculate(String s, int[] idx) {
        int sign = 1, num = 0, sum = 0;
        while(idx[0] < s.length()) {
            char cur = s.charAt(idx[0]);
            if(cur == ' ') {
                idx[0]++;
            } else if(Character.isDigit(cur)) {
                num = num * 10 + cur - '0';
                idx[0]++;
            } else if (cur == '-') {
                sum += sign * num;
                num = 0;
                sign = -1;
                idx[0]++;
            } else if (cur == '+') {
                sum += sign * num;
                num = 0;
                sign = 1;
                idx[0]++;
            } else if (cur == '(') {
                idx[0]++;
                num = calculate(s, idx);
                sum += sign * num;
                num = 0;
            } else if (cur == ')') {
                sum += sign * num;
                idx[0]++;
                num = 0;
                return sum;
            }
        }
        sum += sign * num; 
        return sum;
    }
}
// 前两次submit，"1"和"(1)"两个test case出错。原因是没及时将num清零