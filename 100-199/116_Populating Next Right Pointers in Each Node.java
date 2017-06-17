/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
// DFS
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        if(root.left != null) {
            root.left.next = root.right;
            if(root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
    }
}
// level by level
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        root.next = null;
        TreeLinkNode node = root;
        while(node.left != null) {
            TreeLinkNode nextLevelNode = node.left;
            while(node != null) {
                node.left.next = node.right;
                TreeLinkNode next = node.next;
                if(next != null) node.right.next = next.left;
                else node.right = null;
                node = next;
            }
            node = nextLevelNode;
        }
    }
}