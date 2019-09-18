/*
Given a string, remove all leading/trailing/duplicated empty spaces.

Assumptions:

The given string is not null.
Examples:

“  a” --> “a”
“   I     love MTV ” --> “I love MTV”
*/
public class Solution {
  public String removeSpaces(String input) {
    // Write your solution here
    if (input.isEmpty()) {
      return input;
    }
    // 标准起手式，用SB最后在delete的时候会出小问题，一会讨论
    char[] array = input.toCharArray();
    int end = 0;
    for (int i = 0; i < array.length; i++) {
      //什么时候挪动fast指针？2种情况
      //case1. 自己是空格，并且重复出现时
      //case2. 第一个字符为空格时
      if (array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) {
        continue;
      }
      array[end++] = array[i];
    }
    // 注意此时end指针位置！！
    // case1. 可能end根本没动
    // case2. 可能end移动到了array的末尾还多一位（被++了）
    // 处理完指针位置，再考虑corner case， 如果段尾出现空格，这里需要判断
    if(end > 0 && array[end - 1] == ' ') {
      end--;
    }
    // 注意左开又闭合，end任然在需要取的字母的后一位
    // 这里如果是SB，只能用sb.delete(end + 1,sb.length())，不好想
    return new String(array, 0, end);
  }
}
