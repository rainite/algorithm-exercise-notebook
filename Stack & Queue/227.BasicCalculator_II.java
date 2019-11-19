/**
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5

思路: 符号+数字为一组, +3, -2, *5
所以比如"3+5/2" 默认先在3之前加个加号变成"+3+5/5"比较容易思考
循环内主要分成两种, 一种是数字, 一种符号, 注意顺序一定是符号先,数字后,数字之前的符号确定了该怎么处理这个数字
 */
class Solution {
  public int calculate(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    s = s.trim();
    Deque<Integer> stack = new LinkedList<>();
    int curSum = 0;
    char operator = '+';
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ') {
        continue;
      }
      if (Character.isDigit(s.charAt(i))) {
        curSum = curSum * 10 + s.charAt(i) - '0';
      }
      if (!Character.isDigit(s.charAt(i)) ||  i == s.length() - 1) {
        if (operator == '+') {
          stack.push(curSum);
        } else if (operator == '-') {
          stack.push(-curSum);
        } else if (operator == '*') {
          stack.push(stack.pop() * curSum);
        } else {
          stack.push(stack.pop() / curSum);
        }
        operator = s.charAt(i);
        curSum = 0;
      }
    }
    int res = 0;
    for (int num : stack) {
      res += num;
    }
    return res;
  }
}