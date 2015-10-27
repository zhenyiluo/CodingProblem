public class LRUCache1 {
    HashMap<Integer, Node> hm = new HashMap<Integer, Node>();
    Node head = null;
    Node tail = null;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(hm.containsKey(key)){
            Node node = hm.get(key);
            remove(node);
            setHead(node);
            return node.value;
        }
        return -1;
    }

    public void set(int key, int value) {
        if(hm.containsKey(key)){
            Node old = hm.get(key);
            old.value = value;
            remove(old);
            setHead(old);
        }else{
            Node newNode = new Node(key, value);
            if(hm.size() >= capacity){
                hm.remove(tail.key);
                remove(tail);
            }
            setHead(newNode);
            hm.put(key, newNode);
        }
    }

    private void remove(Node node){
        if(node.prev != null){
            node.prev.next = node.next;
        }else{
            head = node.next;
        }

        if(node.next != null){
            node.next.prev = node.prev;
        }else{
            tail = node.prev;
        }
    }
    private void setHead(Node node){
        node.next = head;
        node.prev = null;
        if(head != null){
            head.prev = node;
        }
        head = node;
        if(tail == null){
            tail = head;
        }
    }
}

class Node{
    int key;
    int value;
    Node prev;
    Node next;
    public Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}