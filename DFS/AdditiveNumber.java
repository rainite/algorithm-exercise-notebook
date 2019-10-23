/**
Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 

Example 1:

Input: "112358"
Output: true
Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
             1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
Example 2:

Input: "199100199"
Output: true
Explanation: The additive sequence is: 1, 99, 100, 199. 
             1 + 99 = 100, 99 + 100 = 199
 

Constraints:

num consists only of digits '0'-'9'.
1 <= num.length <= 35

思路: 不是很常规的DFS, DFS的部分很好想, 就是输入2个加数, 找到一个和之后,把第二个加数变成第一个加数, 把和变成第二个加数递归, 直到string遍历完
特殊的地方在于对于第一组数据需要单独处理, 并且parse不能直接用Long.parseLong, 因为要拍出首字母为0的情况
 */

public class Solution {
  public boolean isAdditiveNumber(String num) {
    // Write your solution here
    if (num == null || num.length() < 3) {
      return false;
    }
    for (int i = 1; i < num.length(); i++) {
      for (int j = i + 1; j < num.length(); j++) {
        long a = parse(num.substring(0, i));
        long b = parse(num.substring(i, j));
        if (a == -1 || b == -1) {
          continue;
        }
        if (helper(num, j, a, b)) {
          return true;
        }
      }
    }
    return false;
  }
  private boolean helper(String num, int index, long first, long second) {
    if (index == num.length()) {
      return true;
    }
    for (int i = index; i < num.length(); i++) {
      long sum = parse(num.substring(index, i + 1));
      if (sum == -1) {
        break;
      }
      if (first + second == sum && helper(num, i + 1, second, sum)) {
        return true;
      }
    }
    return false;
  }
  private long parse(String s) {
    if (!s.equals("0") && s.startsWith("0")) {
      return -1;
    }
    // overflow
    long res = 0;
    try {
      res = Long.parseLong(s);
    } catch (NumberFormatException e) {
      return -1;
    }
    return res;  
  }
}
