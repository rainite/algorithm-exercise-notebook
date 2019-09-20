/*
Right shift a given string by n characters.

Assumptions

The given string is not null.
n >= 0.
Examples

"abc", 4 -> "cab"
经典3步旋转法，从length - n 开始到end做一次，0到length - n - 1 第二次，最后全局翻转
*/

public class Solution {
  public String rightShift(String input, int n) {
    // Write your solution here
    
    if (input == null || input.length() == 0) {
      return input;
    }
    
    char[] array = input.toCharArray();
    n %= array.length;
    // n = 0 is ok here, but don't forget to check
    reverse(array, array.length - n, array.length - 1);
    reverse(array, 0, array.length - n - 1);
    reverse(array,0,array.length - 1);
    return new String(array);
  }
  private void reverse(char[] array, int i, int j) {
    while (i <= j) {
      char temp = array[i];
      array[i++] = array[j];
      array[j--] = temp;
    }
  }
}
