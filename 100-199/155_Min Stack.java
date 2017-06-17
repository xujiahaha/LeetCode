public class MinStack {

    private Stack<Integer> data;
    private Stack<Integer> min;
    
    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<Integer>();
        min = new Stack<Integer>();
    }
    
    public void push(int x) {
        data.push(x);
        if(min.isEmpty()) {
            min.push(x);
        } else {
            min.push(Math.min(x, min.peek()));
        }
    }
    
    public void pop() {
        data.pop();
        min.pop();
    }
    
    public int top() {
        return data.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */