/**
Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.

Assumptions

The given word is not null and is not empty
The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
Examples

Dictionary: {“bob”, “cat”, “rob”}

Word: “robob” return false

Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
 
 特殊：可以有不切的情况，把dp[0] = true 作为corner case 存下
 物理意义：dp[i] 代表 从0到i - 1 能不能break
 递推公式：回头看dp[j] && substring(j,i) 是true则dp[i] = true
 注意这里substring 的index是不包含i的， 正好我们i是从1开始到length() + 1 有1的offset，完美
 */


 class Solution {
  public boolean wordBreak(String input, List<String> dict) {
    // Write your solution here
    boolean[] dp = new boolean[input.length() + 1];
    dp[0] = true;
    Set<String> dictSet = new HashSet(dict);
    for (int i = 1; i < input.length() + 1; i++) {
      for (int j = 0; j < i; j++) {
        if (dictSet.contains(input.substring(j,i)) && dp[j]) {
          dp[i] = true;
          break;            
        }
      }
    }
    return dp[input.length()];
  }
}
