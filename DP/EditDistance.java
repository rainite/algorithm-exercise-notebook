/*
* Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

Assumptions

Both strings are not null
Examples

string one: “sigh”, string two : “asith”

the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).


* */


class Solution {

  public int minDistance(String word1, String word2) {

    int[][] visited = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i < visited.length; i++) {
      for (int j = 0; j < visited[0].length; j++) {
        visited[i][j] = -1;
      }
    }
    visited[0][0] = 0;
    return helper(word1, word2, visited);
  }
  private int helper (String word1, String word2, int[][] visited) {
    // base
    if (word1.length() == 0) {
      return word2.length();
    }
    if (word2.length() == 0) {
      return word1.length();
    }
    int len1 = word1.length();
    int len2 = word2.length();
    if (visited[len1][len2] != -1) {
      return visited[len1][len2];
    }
    if (word1.charAt(0) == word2.charAt(0)) {
      visited[len1][len2] = helper(word1.substring(1),word2.substring(1),visited);
      return visited[len1][len2];
    }
    int replace = 1 + helper(word1.substring(1),word2.substring(1), visited);
    int delete = 1 + helper(word1.substring(1),word2,visited);
    int insert = 1 + helper(word1, word2.substring(1), visited);
    int min = Math.min(replace,Math.min(delete,insert));
    visited[len1][len2] = min;
    return min;
  }
}