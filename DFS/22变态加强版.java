/**
给一个n,打n个 if{} blocks,要求格式:
if {
}
if{
}
<new line>
if {
  if { //前面2个空格

  }
}
疯狂练习吃进去和吐出来,想清楚怎么吃,递归出来就要怎么吐
 */
class Solution {
  int spaceCount = 0;
  public void generateParenthesis(int n) {
    if (n <= 0) {
      return;
    }
    helper(new StringBuilder(), 0, 0, n);
  }
  private void helper(StringBuilder aug, int l, int r, int n) {
    if (l + r == n * 2) {
      System.out.println(aug.toString());
      return;
    }
    if (l < n) {
      for (int i = 0; i < spaceCount; i++) {
        aug.append("  ");
      }
      aug.append("if {\n");
      spaceCount++;
      helper(aug, l + 1, r, n);
      // recovery, 倒序吐出来
      spaceCount--;
      // 注意这里"if {\n"等于吃了5个char
      aug.delete(aug.length() - 5, aug.length());
      for (int i = 0; i < spaceCount; i++) {
        aug.delete(aug.length() - 2, aug.length());
      }

    }
    if (r < l) {
      for (int i = 1; i < spaceCount; i++) {
        aug.append("  ");
      }
      aug.append("}\n");
      spaceCount--;
      helper(aug, l, r + 1, n);
      // recovery
      spaceCount++;
      aug.delete(aug.length() - 2, aug.length());
      for (int i = 1; i < spaceCount; i++) {
        aug.delete(aug.length() - 2, aug.length());
      }
    }
  }
}