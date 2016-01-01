import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.toggle("a");
        print(sol.getSelectedList());
        sol.toggle("b");
        print(sol.getSelectedList());
        sol.toggle("c");
        print(sol.getSelectedList());
        sol.toggle("b");
        print(sol.getSelectedList());
    }

    private static void print(List<String> selectedList) {
        for(String str : selectedList) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    Node head;
    Node tail;
    HashMap<String, Node> cache = new HashMap<>();

    static class Node {
        Node prev;
        Node next;
        String userName;
        public Node(String userName){
            this.userName = userName;
        }
    }

    public void toggle(String userName) {
        if (cache.containsKey(userName)) {
            Node node = cache.get(userName);
            remove(node);
        }
        Node newNode = new Node(userName);
        addToTail(newNode);
    }

    private void remove(Node node) {
        cache.remove(node);
        if(node.prev == null) {
            head.next = node.next;
        } else {
            node.prev.next = node.next;
        }

        if(node.next == null) {
            tail.prev = node.prev;
        } else {
            node.next.prev = node.prev;
        }
    }

    private void addToTail(Node node) {
        node.next = null;
        node.prev = tail;

        if(tail != null) {
            tail.next = node;
        }

        tail = node;

        if(head == null) {
            head = node;
        }
        cache.put(node.userName, node);
    }

    public List<String> getSelectedList() {
        List<String> retList = new LinkedList<>();
        Node cur = head;
        while(cur != null) {
            retList.add(cur.userName);
            cur = cur.next;
        }
        return retList;
    }
}
