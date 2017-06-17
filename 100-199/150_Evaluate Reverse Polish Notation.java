public class Solution {
    // clarification question: do we need to consider invalid input?
    public int evalRPN(String[] tokens) {
        // digit: push to stack
        // operator: pop two numbers from stack -> calculate result -> push result to stack.
        if(tokens == null || tokens.length == 0) return Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("-")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2-num1);
            } else if(tokens[i].equals("+")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2+num1);
            } else if(tokens[i].equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2/num1);
            } else if(tokens[i].equals("*")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2*num1);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}