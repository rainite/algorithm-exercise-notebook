/*
* Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insert operations needed to transform one string into the other.

Assumptions

Both strings are not null
Examples

string one: “sigh”, string two : “asith”

the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).

分析: 发现3个操作背后的物理意义
insert意思为添加一个与word2当前位置相同的字母,等于抵消掉一个word2的字母
delete为删一个word1的字母,从word1的后一个位置再看
replace就是直接把当前字母换成与word2当前位置相同的字母

首先是递归版dfs + memo
level: len(word1) + len(word2)
status: 4种, 
  case1: 当前字母相等,直接返回, 与下面3种相斥
  case2: insert
  case3: delete
  case4: replace
记忆化: 记忆两个单词分别的index,对应的最小edit次数
base: len(word1) == 0 or len(word2) == 0, 全部做insert 或者 delete

DP动归版
物理意义: M[i][j]为从0 ~ i 的word1 编辑到 从0 ~ j 的word2 要多少步骤
induction rule: 如果s1[i] == s2[j] 那么M[i][j] = M[i - 1][j - 1]步数不变
如果不等:
  M[i][j]应该为三种操作的最小值,分析这三种操作
  delete 等于 M[i - 1][j]
  replace 等于 M[i - 1][j - 1]
  insert 等于 M[i][j - 1] 因为抵消了一个word2字母
  所以 M[i][j] = min(delete, replace, insert) + 1 注意加1是本次操作消耗的步数

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