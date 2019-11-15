/**
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

思考: 括号成对出现, 翻译成用stack的条件为
1. 右括号必须跟stack里左括号匹配
2. for 结束stack必须为空(成对匹配)
 */

 class Solution {
  public boolean isValid(String s) {
    Deque<Character> stack = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == '['|| s.charAt(i) == '{'){
        stack.push(s.charAt(i));
      } else {
        char cur = s.charAt(i);
        if (stack.size() == 0) {
          return false;
        } else if (cur == ')' && stack.peek() != '(') {
          return false;
        } else if (cur == ']' && stack.peek() != '[') {
          return false;
        } else if (cur == '}' && stack.peek() != '{') {
          return false;
        } else {
          stack.pop();
        }
      }
    }
    return stack.size() == 0 ? true : false;
  }
}