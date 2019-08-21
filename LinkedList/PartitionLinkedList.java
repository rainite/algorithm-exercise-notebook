/**
Given a linked list and a target value T, partition it such that all nodes less than T are listed before the nodes larger than or equal to target value T. The original relative order of the nodes in each of the two partitions should be preserved.

Examples

L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null

 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode partition(ListNode head, int target) {
    /*
    step 1. split the list to 2 lists, one is larger than target, one is smaller than target
    step 2. connect new lists to one
    step 3. set list end to null, prevent cycle.
    */
    //注意 一层只干这一层的事，只需要考虑这一层什么进来了，要出去什么
    //首先，开篇先考虑核心问题，我们处理这个问题需要哪些东西，根据上面讨论的step，有2个新list，而且都要对头操作，于是有2个傻头
    if (head == null) {
      return head;
    }
    ListNode dummySmall = new ListNode(0);
    ListNode small = dummySmall;
    ListNode dummyLarge = new ListNode(0);
    ListNode large = dummyLarge;

    /* 思考这一层该做什么？ 目的是遍历，拆散原有link关系并重新组织新的link
    1. 退出条件: 所有node遍历结束
    2. 判断node值大小来确定归属
    3. 做完归属后要挪动指针！这里在同时维护small,large,head 3个指针，要注意
    */
    while (head != null) {
      if (head.value < target) {
        small.next = head;
        small = small.next;
      } else {
        large.next = head;
        large = large.next;
      }
      head = head.next;
    }

    //此时 step 1 完成，想想获得了两个list，作为输入，接下来下一层干什么
    small.next = dummyLarge.next;
    //此时，已经连接了两个list，有什么要善后的？
    large.next = null;
    //全部完成
    return dummySmall.next;
  }
}
