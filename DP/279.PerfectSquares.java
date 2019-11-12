/**
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

左大段右小段， linear scan 回头看
m[i] 表示i能用最少多少完全平方数的和构成
 */
class Solution {
  public int numSquares(int n) {
    if (n <= 0) {
      return -1;
    }
    int[] dp = new int[n + 1];
    for (int i = 0; i < dp.length; i++) {
      dp[i] = i;
      for (int j = 1; i - j * j >= 0; j++) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }
    return dp[n];
  }
}