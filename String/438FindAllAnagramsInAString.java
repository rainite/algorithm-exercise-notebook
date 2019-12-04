/**
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

思路：维护好sliding window的物理意义， 判断出现答案的依据：当map记录过的字母--出现了len(p)次时
右挡板++：如果当前字母在map中>0，说明在p中出现过，count--，判断count加res
right++，map--
左挡板：当r-l >= p.length时发生移动
如果当前字母在map中>=0,说明是标记的(没被标记的已经减成负数了)，count++
left++, map++

 */
class Solution {
  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> res = new ArrayList<>();
    int[] map = new int[256];
    for (char c : p.toCharArray()) {
      map[c]++;
    }
    int left = 0, right = 0;
    int count = p.length();
    while (right < s.length()) {
      if (map[s.charAt(right)] > 0) {
        count--;
        if (count == 0) {
          res.add(left);
        }
      }
      map[s.charAt(right)]--;
      right++;
      // delete
      if (right - left >= p.length()) {
        if (map[s.charAt(left)] >= 0) {     
          count++;
        }
        map[s.charAt(left)]++;
        left++;
      }
    }
    return res;
  }
}