/**
Given an integer number, return all possible combinations of the factors that can multiply to the target number.

Example

Give A = 24

since 24 = 2 x 2 x 2 x 3

              = 2 x 2 x 6

              = 2 x 3 x 4

              = 2 x 12

              = 3 x 8

              = 4 x 6

your solution should return

{ { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }

note: duplicate combination is not allowed.

思路, combination sum 变种, 没有一个固定的list去找了, 取代的是一个值, 满足一定条件都可以作为list
这个条件就是这个因数要能被target整除(target % i ==0), 允许因数重复(i在传入下一层时不变)
注意corner case, 因数从2开始检查, 如果aug里只有1个, 代表是target自己, 则不要这个答案
 */
public class Solution {
  public List<List<Integer>> combinations(int target) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<>();
    helper(res, new ArrayList<Integer>(), 2, target);
    return res;
  }
  private void helper(List<List<Integer>> res, List<Integer> aug, int start, int target) {
    if (target <= 1) {
      // 只有他自己的时候就不加了
      if (aug.size() > 1) {
        res.add(new ArrayList<>(aug));
      }
      return;
    }
    for (int i = start; i <= target; i++) {
      if (target % i == 0) {
        aug.add(i);
        helper(res, aug, i, target / i);
        aug.remove(aug.size() - 1);
      }
    }
  }
}
