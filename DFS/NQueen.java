/**
Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.

Assumptions

N > 0
Return

A list of ways of putting the N Queens
Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)
Example

N = 4, there are two ways of putting 4 queens:

[1, 3, 0, 2] --> the Queen on the first row is at y index 1, the Queen on the second row is at y index 3, the Queen on the third row is at y index 0 and the Queen on the fourth row is at y index 2.

[2, 0, 3, 1] --> the Queen on the first row is at y index 2, the Queen on the second row is at y index 0, the Queen on the third row is at y index 3 and the Queen on the fourth row is at y index 1.

 思路: level : n 层
 states: n个, 从index 0 ~ n-1
 主要是判断条件怎么写, 详见代码
 */
public class Solution {
  public List<List<Integer>> nqueens(int n) {
    // Write your solution here
    if (n == 0) {
      return new ArrayList<List<Integer>>();
    }
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> aug = new ArrayList<>();
    helper(res, aug, n);
    return res;
  }
  private void helper(List<List<Integer>> res, List<Integer> aug, int n) {
    //base
    if (aug.size() == n) {
      res.add(new ArrayList<Integer>(aug));
      return;
    }
    for (int i = 0; i < n; i++) {
      if (isValid(aug, i)) {
        aug.add(i);
        helper(res,aug,n);
        aug.remove(aug.size() - 1);
      }    
    }
  }
  private boolean isValid(List<Integer> aug, int column) {
    int row = aug.size();
    for (int i = 0; i < row; i++) {
      // 如果在同一列,不行; 如果在斜线范围,也不行; 斜线是一个正方形,横竖相等
      if (aug.get(i) == column || Math.abs(aug.get(i) - column) == row - i) {
        return false;
      }
    }
    return true;
  }
}
