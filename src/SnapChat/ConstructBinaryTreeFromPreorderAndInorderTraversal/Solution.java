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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
            || preorder.length != inorder.length) {
                return null;
            }
        int len = preorder.length;
        return buildTree(0, len-1, preorder, 0, len-1, inorder);
    }
    
    private TreeNode buildTree(int left1, int right1, int[] preorder, int left2, int right2, int[] inorder) {
        if(right1 < left1 || right2 < left2) {
            return null;
        }
        int val = preorder[left1];
        TreeNode node = new TreeNode(val);
        int pivot = findPos(left2, right2, inorder, val);
        int len = pivot - left2;
        node.left = buildTree(left1 + 1, left1 + len, preorder, left2, pivot - 1, inorder);
        node.right = buildTree(left1 + len + 1, right1, preorder, pivot + 1, right2, inorder);
        return node;
    }
    
    private int findPos(int left, int right, int[] order, int target) {
        for(int i = left; i <= right; i++) {
            if (order[i] == target) {
                return i;
            }
        }
        return -1;
    }
    
}