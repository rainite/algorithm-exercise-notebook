/*
Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. 
Use random5() to implement random1000()

思路: 制造5进制数, random5 * 5 + random 就可以表达0 - 24 等概率的数, 再乘以5 + random5 则可以表达 0 - 124的数
则5 ^ 5 = 3125 -> 用random3125 去构造 random1000
*/
public class Solution {
  public int random1000() {
    // Write your solution here.
    // you can use RandomFive.random5() for generating
    // 0 - 4 with equal probability.

    while (true) {
      int num = 0;
      for (int i = 0; i < 5; i++) {
        num = 5 * num + RandomFive.random5();
      }
      if (num < 3000) {
        return num % 1000;
      }
    }
  }
}
