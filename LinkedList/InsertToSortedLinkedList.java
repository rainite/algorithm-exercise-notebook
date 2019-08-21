/*
Insert a value in a sorted linked list.

Examples

L = null, insert 1, return 1 -> null
L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
L = 2 -> 3 -> null, insert 1, return 1 -> 2 -> 3 -> null
*/
public class Solution {
  public ListNode insert(ListNode head, int value) {
    // Write your solution here
    // 分析题意，插入一个数，那么3种情况，插最前，中间，和最末尾，因为有对头处理的情况，所以用个傻头
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;
    // 想清楚遍历退出条件,这里我们想把cur指针停留在最后一个node上，并且下面会deref cur.next，所以要检查cur.next
    while (cur != null && cur.next != null){
      if (cur.next.value > value){
        // 怎么插入node，算法练熟
        ListNode temp = cur.next;
        cur.next = new ListNode(value);
        cur.next.next = temp;
        return dummy.next;
      }
      // 别忘记挪指针
      cur = cur.next;
    }
    // 这里自带corner case 检测， 如果head是null 也成立
    cur.next = new ListNode(value);
    return dummy.next;
    
  }
}