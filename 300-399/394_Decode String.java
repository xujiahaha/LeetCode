// DFS solution
public class Solution {
    public String decodeString(String s) {
        if(s == null || s.length() == 0) return "";
        int[] k = new int[1];
        return dfs(s, k);
    }
    
    public String dfs(String s, int[] k) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while(k[0] < s.length()) { 
            if(Character.isDigit(s.charAt(k[0]))) { // get repeat number
                num = num * 10 + s.charAt(k[0]++) - '0';
            } else if(s.charAt(k[0]) == '[') { // start a new level
                k[0]++;
                String term = dfs(s, k);
                // add result from next level to current level
                while(num > 0) { 
                    sb.append(term);
                    num--;
                }
            } else if(s.charAt(k[0]) == ']') { // end current level and return result
                k[0]++;
                return sb.toString();
            } else {
                sb.append(s.charAt(k[0]++)); // append char to current result
            }
        }
        return sb.toString();
    }
}
// use stack
public class Solution {
    public String decodeString(String s) {
        String res = "";
        if(s == null || s.length() == 0) return res;
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int i = 0;
        while(i < s.length()) {
            // digit: get the whole number and push to countStack
            if(Character.isDigit(s.charAt(i))) {
                int num = 0;
                while(i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i++) - '0';
                }
                countStack.push(num);
            } 
            // '[': push current res to resStack, res=""
            else if (s.charAt(i) == '[') {
                resStack.push(res);
                res = "";
                i++;
            } 
            // ']': current level is done, build res using resStack.pop() and countStack.pop()
            else if (s.charAt(i) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int j = 0; j < repeatTimes; j++) {
                    temp.append(res);
                }
                res = temp.toString();
                i++;
            } 
            // letters: append to res
            else {
                res += s.charAt(i++);
            }
        }
        return res;
    }
}