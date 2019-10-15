/**
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums toT. The same repeated number may be chosen from C unlimited number of times.

All numbers (including target) will be positive integers.

Elements in a combination (a1, a2, … , ak) must be in non-descending order.

The solution set must not contain duplicate combinations.

Example

          given candidate set 2,3,6,7 and target 7,

    A solution set is:

     [7]

     [2, 2, 3]

思路: status: len(candidates); 每一层, 从记录的index开始往后每个都可以取一次
level: 因为可以重复取数字, target / min(set) 为最大层数
 */
public class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<>();
    if (candidates == null || candidates.length == 0) {
      return res;
    }
    helper(candidates, res, new ArrayList<>(), 0, 0, target);
    return res;
  }
  private void helper(int[] candidates, List<List<Integer>> res, List<Integer> aug, int sum, int index, int target) {
    if (sum == target) {
      res.add(new ArrayList<Integer>(aug));
      return;
    } 
    for (; index < candidates.length; index++) {
      if (sum + candidates[index] <= target) {
        aug.add(candidates[index]);
        helper(candidates, res, aug, sum + candidates[index], index, target);
        aug.remove(aug.size() - 1);
      }
    }
  }
}
