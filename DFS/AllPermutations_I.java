/*
Given a string with no duplicate characters, return a list with all permutations of the characters.

Assume that input string is not null.

Examples

Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]

Set = "", all permutations are [""]

思考： 单词的permutations，标准简单做法就是swap-swap，确定一个字母后，后面的所有字母与当前字母做swap
1. levels? len(word) - 1
2. states? len(word) - current level 做 swap-swap
* */

public class Solution {
  public List<String> permutations(String set) {
    // Write your solution here
    List<String> res = new ArrayList<>();
    helper(res,set.toCharArray(),0);
    return res;
  }
  private void helper(List<String> res, char[] word, int index) {
    if (index == word.length) {
      res.add(new String(word));
    }
    // index 指要变动的元素，index和后面的所有元素swap，所以注意只用一个forloop！
    for (int i = index; i < word.length; i++) {
      swap(word,i,index);
      helper(res, word,index + 1);
      swap(word,i,index);
    }
  }
  private void swap(char[] word, int i, int j) {
    char temp = word[i];
    word[i] = word[j];
    word[j] = temp;
  }
}
