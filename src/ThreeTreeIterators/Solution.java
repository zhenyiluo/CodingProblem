import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node3;
        node1.right = node4;
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        node2.left = node5;
        node2.right = node6;
        InorderBinaryTreeIterator iterator1 = new InorderBinaryTreeIterator(root);
        while(iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }
        System.out.println();


        PreOrderBinaryTreeIterator iterator2 = new PreOrderBinaryTreeIterator(root);
        while(iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");
        }
        System.out.println();

        PostOrderBinaryTreeIterator iterator3 = new PostOrderBinaryTreeIterator(root);
        while(iterator3.hasNext()) {
            System.out.print(iterator3.next() + " ");
        }
        System.out.println();
    }

}

class PostOrderBinaryTreeIterator implements Iterator<Integer> {
    Stack<TreeNode> treeNodeStack = null;

    public PostOrderBinaryTreeIterator(TreeNode root) {
        treeNodeStack = new Stack<>();
        pushToLeafNode(root);
    }

    private void pushToLeafNode(TreeNode cur) {
        while(cur != null) {
            treeNodeStack.push(cur);
            if(cur.left != null) {
                cur = cur.left;
            } else{
                cur = cur.right;
            }
        }
    }
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next nodes!");
        }
        TreeNode node = treeNodeStack.pop();
        int ret = node.val;
        if(!treeNodeStack.isEmpty()){
            TreeNode next = treeNodeStack.peek();
            if(next.left == node) {
                pushToLeafNode(next.right);
            }
        }
        return ret;
    }

    @Override
    public boolean hasNext() {
        return !treeNodeStack.isEmpty();
    }
}

class PreOrderBinaryTreeIterator implements Iterator<Integer> {
    Stack<TreeNode> treeNodeStack;

    public PreOrderBinaryTreeIterator(TreeNode root) {
        treeNodeStack = new Stack<>();
        if(root != null) {
            treeNodeStack.push(root);
        }
    }

    @Override
    public Integer next(){
        if (!hasNext()) {
            throw new NoSuchElementException("No next nodes!");
        }
        TreeNode node = treeNodeStack.pop();
        int ret = node.val;
        if(node.right != null) {
            treeNodeStack.push(node.right);
        }

        if(node.left != null) {
            treeNodeStack.push(node.left);
        }
        return ret;
    }

    @Override
    public boolean hasNext(){
        return !treeNodeStack.isEmpty();
    }
}


class InorderBinaryTreeIterator implements Iterator<Integer> {

    Stack<TreeNode> treeNodeStack;

    public InorderBinaryTreeIterator(TreeNode root) {
        treeNodeStack = new Stack<>();
        pushLeftChildren(root);
    }

    private void pushLeftChildren(TreeNode node) {
        while(node != null) {
            treeNodeStack.push(node);
            node = node.left;
        }
    }

    @Override
    public boolean hasNext(){
        return !treeNodeStack.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next nodes!");
        }
        TreeNode node = treeNodeStack.pop();
        int ret = node.val;
        pushLeftChildren(node.right);
        return ret;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}

