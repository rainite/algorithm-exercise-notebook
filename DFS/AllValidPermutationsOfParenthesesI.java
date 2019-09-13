/*
* Given N pairs of parentheses “()”, return a list with all the valid permutations.

Assumptions

N > 0
Examples

N = 1, all valid permutations are ["()"]
N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
* 
* 思路：
* 1. How many levels? n*2 
* 2. In each level, how many states? 2, either add '(' or ')'.
* 注意对sb的操作，吃了进入下一层，出来后记得吐，也可以用char[]通过index来控制“backtracking” or “还原当前层”这件事。
* 为什么要还原当前层？因为在退回到上一层的时候，应当保证所有变量还原到上一层状态，primitive不用管，但是reference一定得管
* 所以本层做的改动，在当前层代码结束前得全部还原
* */

import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<String> validParentheses(int n) {
    // Write your solution here
    StringBuilder sb = new StringBuilder();
    List<String> res = new ArrayList<>();
    helper(sb,res, 0,0, n);
    return res;
  }

  private void helper(StringBuilder sb, List<String> res, int left, int right, int pairs) {
    if (left + right == pairs * 2) {
      res.add(sb.toString());
    }
    if (left < pairs) {
      sb.append("(");
      helper(sb,res,left + 1, right,pairs);
      sb = sb.deleteCharAt(sb.length() - 1);
    }
    if (right < left) {
      sb.append(")");
      helper(sb,res,left,right+1,pairs);
      sb = sb.deleteCharAt(sb.length() - 1);
    }
  }
}
