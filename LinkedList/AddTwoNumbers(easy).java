/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.  

Example

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)

Output: 7 -> 0 -> 8
*/

public class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    // Write your solution here
    // corner
    if (l1 == null) return l2;
    if (l2 == null) return l1;

    //简单版，直接相加就行，不用把每个list给reverse了,这里写方法是因为本以为要先把l1,l2 reverse
    ListNode head = add(l1,l2);
    return head;
  }

  private ListNode add(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    boolean carry = false;
    //想清楚退出条件，l1还有，l2还有，carry还有，都不能退出！
    while(l1 != null || l2 != null || carry) {
      int digit = 0;
      if (carry) digit += 1;
      //注意避免NPE，分情况讨论
      if (l1 != null && l2 != null) {
        digit += l1.value + l2.value;
      } else if (l1 != null) {
        digit += l1.value;
      } else if (l2 != null) {
        digit += l2.value;
      } 
      carry = digit / 10 == 1 ? true : false;
      digit = digit % 10;
      cur.next = new ListNode(digit);
      //算法执行完了，进入下一步，挪指针，3个都要挪
      //挪指针，再次讨论避免NPE
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
      cur = cur.next;
    }
    return dummy.next;
  }
}
