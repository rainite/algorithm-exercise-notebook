/**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

想法:
最naive的做法, n层, n个status, 会有无数的重复计算 时间复杂度 n^n
优化剪枝: 有一个map去记录当前字符串的答案, 下次再遇到相同的字符串,直接返回
时间复杂度? 注意不是n^3 貌似是dp解法中2次for 然后最后append 一个 string 再加一个n
但是这里很有意思, 最差的情况是, 整个字符串不管怎么分都能在字典找到, 那么问题退化为找这个字符串的subset, 所以总共可能有2^n个答案
在append进list的时候, 这个list有2^n个string需要append, 所以最终worest case 为 n^ 2 * 2 ^ n

level n 
status n
memo map<String, List<String>>  存这个string可被break的结果
分析: 找一个单词能否被break, 并求出结果
例子: abcd
break(abc) && set(d) U break(ab) && set(cd) U break(a) && set(bcd) U set(abcd)
如果可以break, 把break返回回来的结果append右边的字符串, 变成新的结果放到map里
这里没有短路, 必须全部求出来再放map
*/

class Solution {
    Map<String, List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return new ArrayList<String>();ug;
        }
        Set<String> set = new HashSet<>(wordDict);
        return helper(s, set);
    }
    private List<String> helper(String s, Set<String> set) {
        // base
        List<String> newRes = new ArrayList<>();
        if (s.length() == 0) {
            // need for append
            newRes.add("");
            return newRes;
        }
        // memo case
        if (map.containsKey(s)) {
            return map.get(s);
        }
        // status: n
        for (int i = s.length() - 1; i >= 0; i--) {
            String left = s.substring(0, i);
            String right = s.substring(i);
            if (set.contains(right)) {
                List<String> currentRes = helper(left, set);
                for (String curString : currentRes) {
                    String newString = curString.length() == 0 ? curString + right : curString + " " + right;
                    newRes.add(newString);
                }  
            }  
        }
        // write memo with full answers
        map.put(s, newRes);
        return newRes;
    }
}

// n ^ n

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<String>();
        if (s == null || s.length() == 0) {
            return res;
        }
        Set<String> set = new HashSet<>(wordDict);
        helper(s, set, res, new StringBuilder(), 0);
        return res;
    }
    private void helper(String s, Set<String> set, List<String> res, StringBuilder sb, int index) {
        if (index == s.length()) {
            res.add(sb.toString());
            return;
        }
        int size = sb.length();
        for (int i = index + 1; i <= s.length(); i++) {
            String temp = s.substring(index, i);
            if (set.contains(temp)) {
                if (sb.length() == 0) {
                    sb.append(temp);
                } else {
                    sb.append(" " + temp);  
                }
                helper(s, set, res, sb, i);
                sb.setLength(size);
            }  
        }
    }
}