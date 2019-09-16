/*
Determine if the characters of a given string are all unique.

Assumptions

We are using ASCII charset, the value of valid characters are from 0 to 255
The given string is not null
Examples

all the characters in "abA+\8" are unique
"abA+\a88" contains duplicate characters

Basic ideas of bit manupilation:
Bit Tester: x >> k & 1
Bit Setter: x | (1 << k)
Bit Clearer: x & ~(1 << k)

思考： 用一个int 可以记录32个boolean 的信息， 一个ascii有256个可能，所以需要8组int来记录整个ascii字符出现情况
    [[000000000000000000000000000000000],   0 ～ 31
    [000000000000000000000000000000000],    32 ～ 63
    [000000000000000000000000000000000],
    [000000000000000000000000000000000],
    [000000000000000000000000000000000],
    [000000000000000000000000000000000],
    [000000000000000000000000000000000],
    [000000000000000000000000000000000]]    224 ～ 255

    用 char / 32 得到 row， char % 32 得到 column， 再用 bit getter 和 bit setter 搞定

*/

public class Solution {
  public boolean allUnique(String word) {
    // Write your solution here
    int[] bit_vector = new int[8];
    for (char c : word.toCharArray()) {
      int row = c / 32;
      int column = c % 32;
      if (((bit_vector[row] >>> column) & 1) == 1) {
        return false;
      } else {
        bit_vector[row] |= (1 << column);
      }
    }
    return true;
  }
}
