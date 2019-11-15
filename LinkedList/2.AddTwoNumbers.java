/**
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.

标准板子, 记一下
注意处理carry那里, 虽然这里做加法carry不会超过1, 但是这种用法可以应对更多的情况
 */
class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode cur = dummy;
    int carry = 0;
    while (l1 != null || l2 != null || carry != 0) {
      int num1 = l1 == null ? 0 : l1.val;
      int num2 = l2 == null ? 0 : l2.val;
      int sum = num1 + num2 + carry;
      carry = sum / 10;
      cur.next = new ListNode(sum % 10);
      // move pointer
      l1 = l1 == null ? l1 : l1.next;
      l2 = l2 == null ? l2 : l2.next;
      cur = cur.next;
    }
    return dummy.next;
  }
}