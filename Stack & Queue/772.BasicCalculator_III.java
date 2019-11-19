/**
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

思考: 复用II的代码, 添加一个recursive的解决方式
这种方法在特殊情况下，比如(((((((1))))))) 算法退化到n^2
 */
class Solution {
  public int calculate(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    s = s.replaceAll("\\s+","");
    Deque<Integer> stack = new LinkedList<>();
    int curSum = 0;
    char operator = '+';
    for (int i = 0; i < s.length(); ++i) {
      char curChar = s.charAt(i);
      if (Character.isDigit(curChar)) {
        curSum = 10 * curSum + curChar - '0';
      }
      // 在II基础上，多一个判断条件
      if (curChar == '(') {
        int j = i + 1; int level = 1;
        for (; j < s.length(); j++) {
          if (s.charAt(j) == '(') level++;
          if (s.charAt(j) == ')') level--;
          if (level == 0) break;
        }
        curSum = calculate(s.substring(i + 1, j));
        // 注意()括号结束后面会跟符号, 这里故意吧operator变成')'不影响,因为下一次还会读一个符号, 会把')'变掉
        i = j;
      }
      if (!Character.isDigit(s.charAt(i)) ||  i == s.length() - 1) {
        if (operator == '+') {
          stack.push(curSum);
        } else if (operator == '-') {
          stack.push(-curSum);
        } else if (operator == '*') {
          stack.push(stack.pop() * curSum);
        } else if (operator == '/') {
          stack.push(stack.pop() / curSum);
        }
        operator = s.charAt(i);
        curSum = 0;
      }
    }
    int res = 0;
    for (int i : stack) {
      res += i;
    }
    return res;
  }
}