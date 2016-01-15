import java.util.*;
public class LRUCache {
    Node head;
    Node tail;
    static int capacity;
    HashMap<Integer, Node> hm;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        hm = new HashMap<>();
    }

    private void remove(Node node){
        if(node.prev == null){
            head = node.next;
        }else{
            node.prev.next = node.next;
        }

        if(node.next == null){
            tail = node.prev;
        }else{
            node.next.prev = node.prev;
        }
    }

    private void setHead(Node node){
        node.prev = null;
        node.next = head;

        if(head != null){
            head.prev = node;
        }

        head = node;

        if(tail == null){
            tail = node;
        }
    }

    public int get(int key) {
        if(!hm.containsKey(key)){
            return -1;
        }

        Node node = hm.get(key);
        int ret = node.val;
        remove(node);
        setHead(node);
        return ret;
    }

    public void set(int key, int value) {
        if(hm.containsKey(key)){
            Node node = hm.get(key);
            node.val = value;
            remove(node);
            setHead(node);
        }else{
            Node newNode = new Node(key, value);
            if(hm.size() == capacity){
                hm.remove(tail.key);
                remove(tail);
            }
            hm.put(key, newNode);
            setHead(newNode);
        }
    }
}

class Node{
    Node prev;
    Node next;
    int key;
    int val;
    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}