/**
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.

思路: 找环的鼻祖题, 最主要的是证明
1. 慢指正在环中一定没走完一圈就与快指针相遇
    1. 首先一定会相遇, 距离只有可能是奇数或者偶数
    2. 最差情况slow进环,fast在slow前面, 因为fast是slow速度两倍, 走完两圈时slow刚好走完一圈并且又是现在这个位置
    所以slow在走完一圈之前一定相遇了
2. 设slow第一次相遇路程为S,链表到环入口长度为L, 环长R, 入口到第一次相遇距离为x, 相遇点到环入口距离为y, 那么有:
S = L + x
2S = L + nR + x
得到 L = nR - x  =>  L = (n-1)R + (R-x)  =>  L = (n-1)R + y
所以无论n是多少, 再走一个L, 就等于环里面走n圈加一个y, 所以相遇时把慢指针放head重新走一遍就可以找到起点
 */
public class Solution {
  public ListNode detectCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    boolean flag = false;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      // 有点后续遍历的味道, 最好不要先序检查
      if (fast == slow) {
        flag = true;
        break;
      }
    }
    if (flag) {
      slow = head;
      // 从头开始一个一个挪
      while (slow != fast) {
        slow = slow.next;
        fast = fast.next;
      }
      return slow;
    }
    return null;
  }
}