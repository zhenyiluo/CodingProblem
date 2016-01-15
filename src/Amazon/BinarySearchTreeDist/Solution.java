import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by eric on 2016-01-10.
 */

class TreeNode {
    TreeNode left;
    TreeNode right;
    long val;
    public TreeNode(long val) {
        this.val = val;
    }
}

public class Solution {
    static TreeNode root;


    public static void main(String args[] ) throws Exception {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        solve(in, out);
        out.flush();
        out.close();
    }

    /**
     *
     * @param in    InputReader
     * @param out   PrintWriter
     */
    private static void solve(InputReader in, PrintWriter out){
        // Since the value is between [0, 2^32), the value could be long.
        long a = in.nextLong();
        long b = in.nextLong();
        int n = in.nextInt();

        // Since the value is between [0, 2^32), the value could be long.
        long[] values = new long[n];

        // Flags to mark whether a or b exist in the input.
        boolean flagA = false;
        boolean flagB = false;
        for(int i = 0; i < n; i++) {
            values[i] = in.nextLong();
            if(values[i] == a) {
                flagA = true;
            }
            if(values[i] == b) {
                flagB = true;
            }
        }


        root = new TreeNode(values[0]);

        // According to the problem description, there should no duplicate numbers in the input.
        // Otherwise, the output could be ambiguous.
        for(int i = 1; i < n; i++) {
            addVal(root, values[i]);
        }

        // If either a or b does not exist, print "Not found", and exist directly.
        if(!flagA || !flagB) {
            out.println("Not found");
            return;
        }


        TreeNode lcmNode = findLowestCommonAncestor(root, a, b);

        int da = getDistance(lcmNode, a);
        int db = getDistance(lcmNode, b);

        // The distance between two nodes should be the distance from
        // lowest common ancestor to node "a" plus the distance from
        // lowest common ancestor to node "b".
        out.println(da + db);
    }

    /**
     * Get the distance between given node and target value.
     * @param node      the given node (root).
     * @param target    the given target value.
     * @return  the distance between given node and target value.
     */
    private static int getDistance(TreeNode node, long target) {
        int ans = 0;
        while(node.val != target) {
            ans ++;
            if(node.val < target) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return ans;
    }

    /**
     * Find the lowest common ancestor of two nodes.
     * @param root  the root of binary search tree
     * @param a     the value of node a
     * @param b     the value of node b
     * @return      the lowest common ancestor of node a and node b
     */
    private static TreeNode findLowestCommonAncestor(TreeNode root, long a, long b) {
        while (true) {
            if (root.val > a && root.val > b) {
                root = root.left;
            } else if (root.val < a && root.val < b) {
                root = root.right;
            } else {
                return root;
            }
        }
    }

    /**
     * Add value to the binary search tree.
     * @param node      the given node (root).
     * @param val       the value to be added to the binary search tree.
     */
    private static void addVal(TreeNode node, long val) {
        // Assume no duplicate values in the BST
        while (true) {
            if (val < node.val) {
                if(node.left != null){
                    node = node.left;
                } else{
                    node.left = new TreeNode(val);
                    return;
                }

            } else {
                if(node.right != null) {
                    node = node.right;
                } else{
                    node.right = new TreeNode(val);
                    return;
                }
            }
        }

    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
        public static final int BUFFER_SIZE = 32768;

        /**
         * Input stream constructor, using a buffer to optimize IO
         * @param stream    InputStream
         */
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
            tokenizer = null;
        }

        /**
         *
         * @return  next string.
         */
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        /**
         * Get next int
         * @return  next int from input
         */
        public int nextInt() {
            return Integer.parseInt(next());
        }

        /**
         * Get next long
         * @return  next long from input
         */
        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}


