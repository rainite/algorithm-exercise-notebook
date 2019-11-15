/**
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6

思考:遇到K的题, 都有3种做法
1. iterative reduction, 一个一个merge
2. binary reduction, 两两合并
3. play together, k个一起来
这里我们play together
这题是简单版的, 如果不是linkedlist, 是array, 则需要记录这个值是在哪个数组,这样才好放PQ
 */
class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists == null || lists.length == 0) {
      return null;
    }
    if (lists.length == 1) {
      return lists[0];
    }
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> (a.val - b.val));
    for (ListNode n : lists) {
      // sanity check
      if (n != null) {
        pq.add(n);
      }
    }
    while (!pq.isEmpty()) {
      ListNode head = pq.poll();
      cur.next = head;
      head = head.next;
      if (head != null) {
        pq.offer(head);
      }
      cur = cur.next;
    }
    return dummy.next;
  }
}