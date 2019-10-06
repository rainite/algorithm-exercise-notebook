/**
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
思路: n * 2 个level
( ,) 2个states, 条件( 不能超过 n, ) 不能超过 (
 */
class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    if (n <= 0) {
      return res;
    }
    helper(res, new StringBuilder(), 0, 0, n);
    return res;
  }
  private void helper(List<String> res, StringBuilder aug, int l, int r, int n) {
    if (l + r == n * 2) {
      res.add(aug.toString());
      return;
    }
    if (l < n) {
      aug.append("(");
      helper(res, aug, l + 1, r, n);
      aug.deleteCharAt(aug.length() - 1);
    }
    if (r < l) {
      aug.append(")");
      helper(res, aug, l, r + 1, n);
      aug.deleteCharAt(aug.length() - 1);
    }
  }
}