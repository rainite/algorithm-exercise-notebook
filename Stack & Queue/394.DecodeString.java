/**

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

用2个deque, 一个记乘数, 一个记要要乘的string
注意每次遇到'[' 等于新开一个状态, 要新建一个StringBuilder
 */
class Solution {
 public String decodeString(String s) {
    Deque<Integer> nums = new ArrayDeque<>();
    Deque<StringBuilder> result = new ArrayDeque<>();
    result.push(new StringBuilder());
    int num = 0;

    for (char ch : s.toCharArray()) {
       if (Character.isDigit(ch)) {
          num = num * 10 + ch - '0';
       } else if (ch == '[') {
          nums.push(num);
          num = 0; //reset
          result.push(new StringBuilder());
       } else if (ch == ']') {
          StringBuilder str2Multiply = result.pop();
          int times = nums.pop();
          StringBuilder multipliedStr = new StringBuilder();
          for (int j = 0; j < times; j++) {
             multipliedStr.append(str2Multiply);
          }
          result.push(result.pop().append(multipliedStr));
       } else {
          result.push(result.pop().append(ch));
       }
    }
    return result.pop().toString();
 }
}