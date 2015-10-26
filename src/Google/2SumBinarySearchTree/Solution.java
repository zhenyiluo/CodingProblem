import java.util.*;
class TreeNode{
    int val;
    TreeNode left, right;
    TreeNode(int val){
        this.val = val;
    }
}
public class Solution{
    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(17);
        System.out.println(new Solution().getCount(root, 18));
    }

    ArrayList<Integer> list = new ArrayList<Integer>();
    public int getCount(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        dfs(root);
        int size = list.size();
        int first = 0;
        int last = size -1;
        int count = 0;
        while(first < last){
            int tmp = list.get(first) + list.get(last) ;
            if(tmp == sum){
                count ++;
                first ++;
                last --;
            }else if( tmp < sum){
                first ++;
            }else{
                last --;
            }

        }
        return count;
    }

    public void dfs(TreeNode root){
        if(root == null){
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}
