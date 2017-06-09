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