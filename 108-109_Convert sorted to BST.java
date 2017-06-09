// 108-----------------------------------------------------------------------
public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // corner case
        if(nums == null || nums.length == 0) return null;
        
        TreeNode root = buildBST(nums, 0, nums.length-1);
        return root;
    }
    
    public TreeNode buildBST(int[] nums, int start, int end) {
        if(start > end) return null;
        int mid = (start + end) >>> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, start, mid-1);
        root.right = buildBST(nums, mid+1, end);
        return root;
    }
}
//109---------------------------------------------------------------------------
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return buildBST(head, null);
    }
    
    public TreeNode buildBST(ListNode head, ListNode tail) {
        ListNode slow = head, fast = head;
        if(head == tail) return null;
        while(fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = buildBST(head, slow);
        root.right = buildBST(slow.next, tail);
        return root;
    }
}