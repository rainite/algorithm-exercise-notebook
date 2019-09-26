/*
* Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

Assumptions

input, S and T are not null, S is not empty string
Examples

input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
input = "laicode", S = "code", T = "offer", input becomes "laioffer"
* */

//case 1. len(s) >= len(t), i指针直接复写，j指针继续往后找，长度最后调整
//case 2. len(s) < len(t)，长度事先调整，先搜一遍看有几个重复，然后从尾开始往前操作

//!! 这种算法为训练原地算法的时候用，真正面试时，用String.indexOf 和 StringBuilder 直接 append 就好了
// 调用 indexOf(String str, int fromIndex)，从fromIndex开始往后面看，返回第一次出现substring的index，else -1



public class Solution {
  public String replace(String input, String source, String target) {
    // Write your solution here
    if (input == null || input.length() == 0) {
      return input;
    }
    if (source.length() >= target.length()) {
      return replaceA(input, source, target);
    }
    return replaceB(input, source, target);

  }

  private String replaceA(String input, String source, String target) {
    int i = 0,j = 0;
    char[] array = input.toCharArray();
    while (j < array.length) {
      // case 1. array[j] is not source[0] or not a substring, just copy and move pointer 
      if (array[j] != source.charAt(0) || !isSubstring(array,source,j)) {
        array[i++] = array[j++];
      } else {
        // just copy target to array and move pointer
        j += source.length();
        int count = 0;
        while (count < target.length()) {
          array[i++] = target.charAt(count++);
        }
      }
    }
    return new String(array,0,i);
  }

  private String replaceB(String input, String source, String target){
    // 这个case 倒着走pointer
    // 先确定要扩容多少 -> 先确定有多少个substring， 然后再 * 长度差 
    int extraSpace = numOfAppearance(input,source) * (target.length() - source.length());
    char[] array = new char[input.length() + extraSpace];
    // 复制过来
    for (int q = 0; q < input.length(); q++) {
      array[q] = input.charAt(q);
    }
    int i = array.length - 1, j = input.length() - 1;
    
    while (j >= 0) {
      // 不相等或者不是substring，注意传入pointer的位置！
      if (array[j] != source.charAt(source.length() - 1) || !isSubstring(array,source,j - source.length() + 1)) {
        array[i--] = array[j--];
        // 复制
      } else {
        j -= source.length();
        for (int k = target.length() - 1; k >= 0; k--) {
          array[i--] = target.charAt(k);
        }
      }
    }
    // 因为扩容了所以肯定要返回整个array
    return new String(array);
  }

  private boolean isSubstring(char[] array, String source, int i) {
    // ！！注意这里corner case判断！！ i会挪动一个source身位
    if (i < 0 || i + source.length() > array.length) {
      return false;
    }
    for (int k = 0; k < source.length(); k++) {
      if (array[i++] != source.charAt(k)) {
        return false;
      }
    }
    return true;
  }

  private int numOfAppearance(String input, String source) {
    int count = 0;
    char[] array = input.toCharArray();
    for (int i = 0; i < array.length; i++) {
      if (array[i] == source.charAt(0) && isSubstring(array, source, i)) {
        count++;
      }
    }
    return count;
  }
}

// Better solution
public class Solution {
  public String replace(String input, String source, String target) {
    // Write your solution here
    if (input == null || input.length() == 0) {
      return input;
    }
    StringBuilder sb = new StringBuilder();
    int fromIndex= 0;
    int matchIndex = input.indexOf(source,fromIndex);
    while (matchIndex != -1) {
      // 先把match到index之前的全部append了,再把target append了
      sb.append(input,fromIndex,matchIndex).append(target);
      // 挪动指针
      fromIndex = matchIndex + source.length();
      matchIndex = input.indexOf(source,fromIndex);
    }
    // 此时出现source的地方已经解决了，直接处理剩下的
    sb.append(input,fromIndex,input.length());
    return sb.toString();
  }
}