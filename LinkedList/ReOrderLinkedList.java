/*
Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null

Examples

L = null, is reordered to null
L = 1 -> null, is reordered to 1 -> null
L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
*/
// 综合题，分析题意，先中点拆分，再把后面的list reverse了，在一个一个拼接成新的list
public class Solution {
  public ListNode reorder(ListNode head) {
    // Write your solution here
    if (head == null || head.next == null) {
      return head;
    }
    // 
    ListNode preMid = findMid(head);
    //注意找到mid了之后，断开原有list时注意接地
    ListNode mid = preMid.next;
    preMid.next = null;
    mid = reverse(mid);
    return combine(head,mid);
  }

  private ListNode findMid(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while(fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private ListNode reverse(ListNode head) {
    if(head.next == null) {
      return head;
    }
    ListNode tail = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return tail;
  }

  private ListNode combine(ListNode one, ListNode two) { 
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    while(one != null && two != null) {
      cur.next = one;
      one = one.next;
      cur = cur.next;
      cur.next = two;
      two = two.next;
      cur = cur.next;
    }
    while (one != null) {
      cur.next = one;
      one = one.next;
      cur = cur.next;
    }
    while (two != null) {
      cur.next = two;
      two = two.next;
      cur = cur.next;
    }
    return dummy.next;
  }
}
