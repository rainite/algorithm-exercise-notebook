/**
Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.

Arguments

coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
target - a non-negative integer representing the target number of cents, eg. 99
Assumptions

coins is not null and is not empty, all the numbers in coins are positive
target >= 0
You have infinite number of coins for each of the denominations, you can pick any number of the coins.
Return

a list of ways of combinations of coins to sum up to be target.
each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
Examples

coins = {2, 1}, target = 4, the return should be

[

  [0, 4],   (4 cents can be conducted by 0 * 2 cents + 4 * 1 cents)

  [1, 2],   (4 cents can be conducted by 1 * 2 cents + 2 * 1 cents)

  [2, 0]    (4 cents can be conducted by 2 * 2 cents + 0 * 1 cents)

]

思路：
Levels: len(coin), 每一层负责取一个
States: Dynamic, but less or equals to target, 看取多少个
注意最后一层可以优化，直接计算到能不能除尽，若可以加这个答案，不行就算了，添加答案时注意添加了当前值，return前一定要还原！
 */

public class Solution {
  public List<List<Integer>> combinations(int target, int[] coins) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> nums = new ArrayList<>();
    helper(res,nums,target,coins, 0);
    return res;
  }
  private void helper(List<List<Integer>> res, List<Integer> nums, int target, int[] coins, int index) {
    if (index == coins.length - 1) {
      if (target % coins[index] == 0) {
        nums.add(target / coins[index]);
        res.add(new ArrayList<>(nums));
        nums.remove(nums.size() - 1);
      }
    }

    for (int i = 0; i * coins[index] <= target; i++) {
      nums.add(i);
      helper(res, nums,target -i * coins[index], coins, index + 1);
      nums.remove(nums.size() - 1);
    }
  }
}
