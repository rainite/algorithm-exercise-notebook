/**
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
思路:
物理意义 dp[r] 代表从s[0,r]能构成回文的最小cut
induction rule: dp[r] = min(dp[r], dp[l] + 1)推动l左挡板, 衍生一个dp[0] = 0方便写rule
termination: return dp[s.length()]
 */

class Solution {
  public int minCut(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int[] dp = new int[s.length() + 1];
    for (int i = 0; i <= s.length(); i++) {
      // initial, 每个字母都切的情况
      dp[i] = i;
      for (int j = i - 1; j >= 0; j--) {
        String sub = s.substring(j, i);
        if (valid(sub)) {
          dp[i] = Math.min(dp[i], dp[j] + 1);
        }
      }
    }
    // 我们的dp公式在最后找到答案时,多'切'了一刀,把这一刀减了
    return dp[s.length()] - 1;
  }
  private boolean valid(String s) {
    int a = 0, b = s.length() - 1;
    while (a < b) {
      if (s.charAt(a) != s.charAt(b)) {
        return false;
      }
      a++;b--;
    }
    return true;
  }

}