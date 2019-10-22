/*
Given a telephone keypad, and an int number, print all words which are possible by pressing these numbers, the output strings should be sorted.

{0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 : "pqrs", 8 : "tuv", 9 : "wxyz"} 

Assumptions:

The given number >= 0
Examples:

if input number is 231, possible words which can be formed are:

[ad, ae, af, bd, be, bf, cd, ce, cf]
思考:
level: len(num)
status: len(map[num]) 特殊的如果len == 0, 直接进入下一层
*/
public class Solution {
  public String[] combinations(int number) {
    // Write your solution here
    Map<Integer, String> map = new HashMap<>();
    map.put(0,""); map.put(1,""); map.put(2,"abc"); map.put(3,"def");
    map.put(4,"ghi"); map.put(5,"jkl"); map.put(6,"mno"); map.put(7,"pqrs");
    map.put(8,"tuv"); map.put(9,"wxyz");
    List<String> res = new ArrayList<>();
    if (number == 0) {
      return res.toArray(new String[]{});
    }
    String num = String.valueOf(number);
    helper(num, map, res, new StringBuilder(), 0);
    return res.toArray(new String[]{});
  }
  private void helper(String num, Map<Integer, String> map, List<String> res, StringBuilder sb, int index) {
    if (index == num.length()) {
      res.add(sb.toString());
      return;
    }
    // 注意 char -> int 背下来
    String chars = map.get(num.charAt(index) - '0');
    // Special case!!
    if (chars.length() == 0) {
      helper(num, map, res, sb, index + 1);
    } else {
      for (int i = 0; i < chars.length(); i++) {
        sb.append(chars.charAt(i));
        helper(num, map, res, sb, index + 1);
        sb.setLength(sb.length() - 1);
      }
    }
  }
}
