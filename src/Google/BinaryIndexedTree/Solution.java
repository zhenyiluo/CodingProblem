import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] A = {4, 1, 3, 2};
        for (int val : new Solution().count(A)) {
            System.out.print(val + " ");
        }
    }

    public ArrayList<Integer> count(int[] A) {
        int len = A.length;
        SegmentTree root = build(1, len);
        ArrayList<Integer> ret = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            update(root, A[i]);
            if (A[i] == 0) {
                ret.add(0, 0);
            } else {
                ret.add(0, query(root, 0, A[i] - 1));
            }
        }
        return ret;
    }

    public SegmentTree build(int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTree root = new SegmentTree(start, end);
        if (start == end) {
            return root;
        }
        int mid = start + ((end - start) >> 1);
        root.left = build(start, mid);
        root.right = build(mid + 1, end);
        return root;
    }

    public int query(SegmentTree root, int start, int end) {
        if (root == null || start > root.end || end < root.start) {
            return 0;
        }
        if (start <= root.start && end >= root.end) {
            return root.count;
        }
        int mid = root.start + ((root.end - root.start) >> 1);
        if (mid >= end) {
            return query(root.left, start, end);
        } else if (mid < start) {
            return query(root.right, start, end);
        } else {
            return query(root.left, start, mid) + query(root.right, mid + 1, end);
        }
    }

    public void update(SegmentTree root, int p) {
        if (root == null) {
            return;
        }
        if (root.start == p && root.end == p) {
            root.count++;
            return;
        }
        int mid = root.start + ((root.end - root.start) >> 1);
        if (mid >= p) {
            update(root.left, p);
        } else {
            update(root.right, p);
        }
        root.count = 0;
        if (root.left != null) {
            root.count += root.left.count;
        }
        if (root.right != null) {
            root.count += root.right.count;
        }
    }

}

class SegmentTree {
    int start;
    int end;
    int count;
    SegmentTree left, right;

    public SegmentTree(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

