// O(n) time, O(n) space when the tree is like a list
// 94. inorder traversal
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
// 144. preorder traversal
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                res.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            cur = cur.right;
        }
        return res;
    }
}
// 145. postorder traversal
// using two stacks
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        st1.push(root);
        while(!st1.isEmpty()){
            TreeNode cur = st1.pop();
            if(cur.left != null){
                st1.push(cur.left);
            }
            if(cur.right != null){
                st1.push(cur.right);
            }
            st2.push(cur);
        }
        while(!st2.isEmpty()){
            res.add(st2.pop().val);
        }
        return res;
    }
}
// using one stack
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        Stack<TreeNode> st = new Stack();
        TreeNode cur = root, pre = null;
        while(cur != null || !st.isEmpty()) {
            if(cur != null) {
                st.push(cur);
                cur = cur.left;
            }else{
                TreeNode topNode = st.peek();
                if(topNode.right == null || topNode.right == pre) {
                    list.add(topNode.val);
                    pre = topNode;
                    st.pop();
                }else{
                    cur = topNode.right;
                }
            }
        }
        return list;
    }
}