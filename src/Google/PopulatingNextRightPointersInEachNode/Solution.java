/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution{

    public void connect(TreeLinkNode root){
        if(root == null){
            return ;
        }

        if(root.right != null){
            if(root.left != null){
                root.left.next = root.right;
            }
            root.right.next = getNextNode(root);
        }else if(root.left != null){
            root.left.next = getNextNode(root);
        }
        connect(root.right);
        connect(root.left);

    }

    public TreeLinkNode getNextNode(TreeLinkNode node){
        while(node.next != null){
            if(node.next.left != null){
                return node.next.left;
            }
            if(node.next.right != null){
                return node.next.right;
            }
            node = node.next;
        }
        return null;
    }
}