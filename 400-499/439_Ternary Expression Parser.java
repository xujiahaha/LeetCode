public class Solution {
    public String parseTernary(String expression) {
        if(expression == null || expression.length() == 0) return null;
        
        int i = expression.length()-1;
        Stack<Character> stack = new Stack<>();
        while(i >= 0) {
            char cur = expression.charAt(i);
            // ':' -> do nothing
            if(cur == ':') {
                i--;
                continue;
            }
            // '?' -> check charAt(i-1). if 'T', pop second item out; otherwise, pop top one
            if(cur == '?') {
                char next = expression.charAt(i-1);
                if(next == 'T') {
                    char temp = stack.pop();
                    stack.pop();
                    stack.push(temp);
                } else {
                    stack.pop();
                }
                i -= 2;
            } else { 
            // '0-9' or 'T/F' located at the left or right side of ':', just push to stack
                stack.push(cur);
                i--;
            }
        }
        return String.valueOf(stack.pop());
    }
}