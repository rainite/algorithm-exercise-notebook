/**
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

E.g.    Input: n = 4, k = 2

    Output: [

                     [2,4],

                     [3,4],

                     [2,3],

                     [1,2],

                     [1,3],

                     [1,4]

        ]

 思考:
 与permutation的区别就是, 这里用过的数就不能再用了
 level: k
 states: n, 递减 用一个i去track
 */
public class Solution {
  public List<List<Integer>> combine(int n, int k) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> aug = new ArrayList<>();
    helper(res, aug, n, k, 1);
    return res;
  }
  private void helper(List<List<Integer>> res, List<Integer> aug, int n, int k, int i) {
    if (aug.size() == k) {
      res.add(new ArrayList<Integer>(aug));
      return;
    }

    for (; i <= n; i++) {
      aug.add(i);
      helper(res,aug,n,k,i + 1);
      aug.remove(aug.size() - 1);
    }
  }
}
