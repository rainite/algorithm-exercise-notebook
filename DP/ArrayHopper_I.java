/*
* Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from that position (you can only jump towards the end of the array). Determine if you are able to reach the last index.

Assumptions

The given array is not null and has length of at least 1.
Examples

{1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)

{2, 1, 1, 0, 2}, we are not able to reach the end of array
*
* 物理意义： dp[i] = 从0到i能不能到达
* 递推公式： dp[i] = any(dp[j] && array[j] >= distance(i,j) for j in range(0, i + 1))
* */
public class Solution {
  public boolean canJump(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return false;
    }
    boolean[] dp = new boolean[array.length];
    dp[0] = true;
    for (int i = 0; i < array.length; i++) {
      for (int j = i; j >= 0; j--) {
        if (dp[j] == true && array[j] >= i - j) {
          dp[i] = true;
          break;
        }
      }
    }
    return dp[array.length - 1];
  }
}
