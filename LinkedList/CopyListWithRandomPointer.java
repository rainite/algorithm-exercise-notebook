/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
思路: 根据遍历顺序, 用两个指针指向原始链和新链, 用map存原始node 和新 node 的对应关系, 
两条链同时遍历, 发现next,或者random 
1. 先检查是否需要复制节点
2. 从map中取出新链的节点, 把原始关系复制上去
*/
class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Map<Node,Node> map = new HashMap<>();
        Node newNode = new Node(head.val,null,null);
        Node res = newNode;
        map.put(head, newNode);
        while (head != null) {
            if (head.next != null) {
                if (!map.containsKey(head.next)) {
                    Node newNext = new Node(head.next.val,null,null);
                    map.put(head.next, newNext);
                }
                newNode.next = map.get(head.next);
            }
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    Node newRandom = new Node(head.random.val,null,null);
                    map.put(head.random, newRandom);
                }
                newNode.random = map.get(head.random);
            }
            // move the pointer
            head = head.next;
            newNode = newNode.next;
        }
        return res;
    }
    
}