/**
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. Each number in C may only be used once in the combination.

All numbers (including target) will be positive integers.

Elements in a combination (a1, a2, … , ak) must be in non-descending order.

The solution set must not contain duplicate combinations.

Example

          given candidate set 10,1,2,7,6,1,5 and target 8,

          A solution set is:

          [1, 7]

          [1, 2, 5]

          [2, 6]

          [1, 1, 6]
与I的区别就是注意去重的问题, 如何去重?
从当前startIndex往后遍历时, 如果遇到前后两个数相等, 且必须 i > startIndex 时, continue
 */
public class Solution {
  public List<List<Integer>> combinationSum2(int[] num, int target) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<>();
    if (num == null || num.length == 0) {
      return res;
    }
    Arrays.sort(num);
    helper(num, res, new ArrayList<Integer>(), 0, 0, target);
    return res;
  }
  private void helper(int[] num, List<List<Integer>> res, List<Integer> aug, int sum, int index, int target) {
    if (sum == target) {
      res.add(new ArrayList<Integer>(aug));
      return;
    }
    // 不能直接用index了, 这里要对比i 和传入的index来确定重复
    // 想清楚重复的触发条件, 是在当前层遇到2个相同的数, 会做出重复的计算,
    // 在不同层, 是不会出现的!
    for (int i = index; i < num.length; i++) {
      if (i > index && num[i] == num[i - 1]) {
        continue;
      }
      int temp = sum + num[i];
      if (temp <= target) {
        aug.add(num[i]);
        helper(num, res, aug, temp, i + 1, target);
        aug.remove(aug.size() - 1);
      }
    }
  }
}
