  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }

public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode manager = new TreeNode(10);
        root.left = manager;
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(3);
        p.right = q;
        manager.left = p;

        System.out.println(new Solution().lowestCommonAncestor(root, p, q).val);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestor(root, p, q, null);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q, TreeNode parent) {
        if(root == null){
            return null;
        }

        if(root == p || root == q){
            return parent;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q, root);
        TreeNode right = lowestCommonAncestor(root.right, p, q, root);

        if(left != null && right != null){
            return root;
        }

        if(left == null){
            return right;
        }else{
            return left;
        }
    }
}