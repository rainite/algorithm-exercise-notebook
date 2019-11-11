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
public class Solution {  
  public Node copyRandomList(Node head) {
    if (head == null) {
      return null;
    }
    HashMap<Node, Node> map = new HashMap<Node, Node>();
    Node oldNode = head;
    Node newNode = new Node(oldNode.val);
    map.put(oldNode, newNode);
    while (oldNode != null) {
      if (oldNode.random != null) {
        // copy node
        map.putIfAbsent(oldNode.random, new Node(oldNode.random.val, null, null));
        // copy edge
        newNode.random = map.get(oldNode.random);
      }
      if (oldNode.next != null) {
        map.putIfAbsent(oldNode.next, new Node(oldNode.next.val, null, null));
        newNode.next = map.get(oldNode.next);
      }
      // move pointer
      oldNode = oldNode.next;
      newNode = newNode.next;
    }
    return map.get(head);
  }
}