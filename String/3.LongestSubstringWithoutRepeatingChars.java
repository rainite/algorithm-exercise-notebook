/**
Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

思路： sliding window， 用一个set去记重
注意这里用map记index， 然后挪pointer查表跳的作用不大， 因为主要是用这个“去重器”来维持sliding window 的物理意义， 只挪index没有意义
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int slow = 0, fast = 0;
        int max = 0;
        while (fast < s.length()) {
            if (set.contains(s.charAt(fast))) {
                // move window
                set.remove(s.charAt(slow++));
            } else {
                max = Math.max(max, fast - slow + 1);
                set.add(s.charAt(fast++));
            }
        }
        return max;
        
    }
}