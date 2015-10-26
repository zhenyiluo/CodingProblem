import java.util.*;
class TreeNode{
    int val;
    TreeNode left, right;
    TreeNode(int val){
        this.val = val;
    }
}
public class Solution1{
    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(17);
        System.out.println(new Solution().getCount(root, 15));
    }

    public int getCount(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        int count = 0;
        Iterator<Integer> iterator1 = new InOrderBinaryTreeIterator(root);
        Iterator<Integer> iterator2 = new InverseInOrderBinaryTreeIterator(root);
        int start = iterator1.next();
        int end = iterator2.next();
        while(start < end){
            int tmp = start + end;
            if(tmp == sum){
                count++;
                start = iterator1.next();
                end = iterator2.next();
            }else if(tmp < sum){
                start = iterator1.next();
            }else{
                end = iterator2.next();
            }
        }
        return count;
    }
}

class InOrderBinaryTreeIterator implements Iterator<Integer>{
    Stack<TreeNode> stack = new Stack<>();
    private void pushLeftChildren(TreeNode cur) {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }
    public InOrderBinaryTreeIterator(TreeNode root){
        pushLeftChildren(root);

    }
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        TreeNode node = stack.pop();
        pushLeftChildren(node.right);
        return node.val;
    }
}

class InverseInOrderBinaryTreeIterator implements Iterator<Integer>{
    Stack<TreeNode> stack = new Stack<>();
    private void pushRightChildren(TreeNode cur) {
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
    }
    public InverseInOrderBinaryTreeIterator(TreeNode root) {
        pushRightChildren(root);
    }
    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        TreeNode node = stack.pop();
        pushRightChildren(node.left);
        return node.val;
    }
}