/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int k = lists.length;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        PriorityQueue<ListNode> pq = new PriorityQueue<>(k, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode node1, ListNode node2){
                return node1.val - node2.val;
            }
        });

        for(int i = 0; i < k; i++) {
            if(lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            if(node.next != null) {
                pq.add(node.next);
            }
            node.next = null;
            cur.next = node;
            cur = cur.next;
        }

        return dummy.next;
    }
}