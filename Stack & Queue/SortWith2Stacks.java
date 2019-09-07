/*
Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:

The given stack is not null.
There can be duplicated numbers in the give stack.
Requirements:

No additional memory, time complexity = O(n ^ 2)

思路是先在buffer里面倒序排出来，用个temp，然后按照算法不停的挪，最后挪回input
算法具体就是，input取一个，存temp，跟buffer第一个比较一下，小于的就直接放，
大于就把buffer取出放入input到小于temp为止，然后放temp，进入下一次循环。
*/

public class Solution {
  public void sort(LinkedList<Integer> s1) {
    LinkedList<Integer> s2 = new LinkedList<Integer>();
    // Write your solution here. 
    if (s1 == null || s1.size() <= 1) {
      return;
    }
    sort(s1,s2);
    
  }

  private void sort(Deque<Integer> input, Deque<Integer> buffer) {
    int temp = 0;
    while (!input.isEmpty()) {
      temp = input.removeFirst();
      if (buffer.isEmpty() || buffer.getFirst() <= temp) {
        buffer.addFirst(temp);
      } else {
        while (!buffer.isEmpty() && buffer.getFirst() > temp) {
          input.addFirst(buffer.removeFirst());
        }
        buffer.addFirst(temp);
      }
    }
    while (!buffer.isEmpty()) {
      input.addFirst(buffer.removeFirst());
    }
  }
}