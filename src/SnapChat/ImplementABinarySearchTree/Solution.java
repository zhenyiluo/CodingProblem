public class Solution{
    private Node root;

    static class Node{
        Node left;
        Node right;
        K key;
        V value;
        int N;
        public Node(K key, V value, int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if(x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    public void put(K key, V value) {
        if(key == null) {
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if(cmp < 0) {
            x.left = put(x.left, key, value);
        }else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else{
            x.value = value;
        }
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null) {
            return 0;
        }
        return node.N;
    }
}