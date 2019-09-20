/*
* Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

Examples

"abbbaaccz" → "aaaccz" → "ccz" → "z"
"aabccdc" → "bccdc" → "bdc"

思路： String的题，用双指针一定要想清楚有多少个case，每个case怎么处理
* 物理意义：end为被检查位，i为待检查位，所以i应该从1开始
* 首先i是快指针，end是慢指针，针对快指针要遍历整个array，可以写进for循环，每操作一次循环一次，end手工挪
Case 1. if s[end] is not s[i]: end++; s[end] = s[i];
Case 2. if s[end] is s[i]:
    end不动，动i去找到最后一个和end相等的点,此时i指向第一个不等于end的点， 抵消掉与end之间的，end--
    这里做end--时敏感的嗅觉告诉你，有越界风险，可能导致end < 0，如果end小于0，那么++来让它有意义
extra case: if end < 0: end++

现在考虑优化问题，case1 和 extra case里都有end++，其实可以合并，因为case1的目的是给end的下一位赋值，
只是说当前位是空，那肯定不是与是s[i]相等的情况，所以添加边界条件，放入case1

注意这里end的挪动，始终是‘被动’挪动，而i则是‘主动’，因为i负责去检测前面的东西和end是不是一样的，
不一样才写入end的下一位，在要写的时候才end++

*/

public class Solution {
  public String deDup(String input) {
    // Write your solution here
    if (input.isEmpty() || input.length() == 1) {
      return input;
    }
    char[] array = input.toCharArray();
    int end = 0;
    for (int i = 1; i < array.length; i++) {
      if (end == -1 || array[i] != array[end]) {
        array[++end] = array[i];
      } else {
        end--;
        while (i + 1 < array.length && array[i] == array[i + 1]) {
          i++;
        }
      }
    }
    return new String(array, 0, end + 1);
  }
}
