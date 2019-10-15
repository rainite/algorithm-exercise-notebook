/**
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

思路:(lc 超时) permutation II 加检测 palindrome, Time: O(n! * n)

 */
public class Solution {
  public List<String> generatePalindromes(String input) {
    // Write your solution here
    List<String> res = new ArrayList<>();
    if (input == null || input.length() == 0) {
      return res;
    }
    helper(res, input.toCharArray(), 0);
    return res;
  }
  private void helper(List<String> res, char[] array, int index) {
    if (index == array.length && isPalindrome(array)) {
      res.add(new String(array));
    }
    Set<Character> used = new HashSet<Character>();
    for (int i = index; i < array.length; i++) {
      if (used.add(array[i])) {
        swap(array, index, i);
        helper(res, array, index + 1);
        swap(array, index, i);
      }
    }
  }
  private boolean isPalindrome(char[] array) {
    int i = 0, j = array.length - 1;
    while (i < j) {
      if (array[i] != array[j]) {
        return false;
      }
      i++; j--;
    }
    return true;
  }
  private void swap(char[] array, int i, int j){
    char temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
