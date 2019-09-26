/*
Given a string with characters, return a list with all permutations of the characters.

Assume that input string is not null.

Examples

Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]

Set = "", all permutations are [""]

思考： 单词的permutations，标准简单做法就是swap-swap，确定一个字母后，后面的所有字母与当前字母做swap
1. levels? len(word) - 1
2. states? len(word) - current level 做 swap-swap
3. 注意开设一个set去重（abb这种，第二个b就不用swap-swap了）
* */

public class Solution {
  public List<String> permutations(String set) {
    // Write your solution here
    List<String> res = new ArrayList<>();
    helper(res, set.toCharArray(), 0);
    return res;
  }
  private void helper(List<String> res, char[] array,int index) {
    if (index == array.length) {
      res.add(new String(array));
      return;
    }
    Set<Character> set = new HashSet<>();
    // index 指要变动的元素，index和后面的所有元素swap，所以注意只用一个forloop！
    for (int i = index; i < array.length; i++) {
      if (set.contains(array[i])) {
        continue;
      }
      set.add(array[i]);
      swap(array,index,i);
      helper(res, array, index + 1);
      swap(array,index,i);
    }
  }
  private void swap (char[] array, int i, int j) {
    char temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}