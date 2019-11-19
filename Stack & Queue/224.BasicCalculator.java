/**
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23

思路: III的简化版
 */
class Solution {
  public int calculate(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    s = s.replaceAll("\\s+", "");
    Deque<Integer> stack = new LinkedList<>();
    int sum = 0;
    char operator = '+';
    for (int i = 0; i < s.length(); i++) {
      char cur = s.charAt(i);
      if (Character.isDigit(cur)) {
        sum = sum * 10 + cur - '0';
      }
      if (cur == '(') {
        int j = i + 1; int level = 1;
        for (; j < s.length(); j++) {
          if (s.charAt(j) == '(') level++;
          else if (s.charAt(j) == ')') level--;
          if (level == 0) break;
        }
        sum = calculate(s.substring(i + 1, j));
        i = j;
      }
      if (!Character.isDigit(cur) || i == s.length() - 1) {
        if (operator == '+') {
          stack.push(sum);
        } else if (operator == '-') {
          stack.push(-sum);
        }
        operator = cur;
        sum = 0;
      }
    }
    return stack.stream().reduce((a,b) -> (a + b)).get();
  }
}