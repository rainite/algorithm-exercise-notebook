/**
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

思路: 建模发现,就是找一系列操作后, 会不会最终结果是1, 或者出现之前出现的过的数. 用Set来判重? 仔细想想是不是和什么很类似
其实是判断linkedlist环的题, 快慢指针搞定
 */

class Solution {
  public boolean isHappy(int n) {
    int slow = n;
    int fast = happy(n);
    while (slow != 1 && slow != fast) {
      slow = happy(slow);
      fast = happy(happy(fast));
    }
    return fast == 1;
  }
  private int happy(int n) {
    int res = 0;
    while (n != 0) {
      res += (n % 10) * (n % 10);
      n /= 10;
    }
    return res;
  }
}