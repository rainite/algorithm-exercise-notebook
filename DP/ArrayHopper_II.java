/*
Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array).
Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.

Assumptions

The given array is not null and has length of at least 1.
Examples

{3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)

{2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.

* 物理意义： dp[i] = 从0到i最短步数
* 递推公式： dp[i] = min(dp[j] + 1 if dp[j] is not -1 and array[j] >= distance(i,j) for j in range(0, i + 1))
* */

public class Solution {
  public int minJump(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return -1;
    }
    int[] dp = new int[array.length];
    dp[0] = 0;
    for (int i = 1; i < array.length; i++) {
      // 巧妙的初始化
      dp[i] = -1;
      for (int j = i - 1; j >= 0; j--) {
        if (dp[j] != -1 && array[j] >= i - j) {
          // 没办法用Math.min？ 没事写个if来模拟min的处理
          if (dp[i] == -1 || dp[i] > dp[j] + 1) {
            dp[i] = dp[j] + 1;
          }
        }
      }
    }
    return dp[array.length - 1];
  }
}