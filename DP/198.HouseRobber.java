/**
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

很直观
 */
class Solution {
  //dp[i] = max(dp[i - 1] + dp[i - 2] + a[i]);
  public int rob(int[] num) {
    int prev1 = 0;
    int prev2 = 0;
    int cur = 0;
    for (int i = 0; i < num.length; i++) {
      cur = Math.max(prev1, prev2 + num[i]);
      prev2 = prev1;
      prev1 = cur;
    }
    return cur;
  }
}

/** House Robber II
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

分成2块，rob头到结尾-1，或者头+1到结尾
 */

class Solution {
  //dp[i] = max(dp[i - 1] + dp[i - 2] + a[i]);
  public int rob(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    return Math.max(helper(nums, 0, nums.length - 1), helper(nums, 1, nums.length));
  }
  
  public int helper(int[] num, int i, int j) {
    int prev1 = 0;
    int prev2 = 0;
    int cur = 0;
    for (; i < j; i++) {
      cur = Math.max(prev1, prev2 + num[i]);
      prev2 = prev1;
      prev1 = cur;
    }
    return cur;
  }
}