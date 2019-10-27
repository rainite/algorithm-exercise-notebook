/**
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.

思考: 这个题必须严格意义上s每个字母要与t的每个字母映射, 可以建立map去追踪这个映射关系, 找到当前对应的char
case 1. a不在map里, 且b也不在, 建立关系
case 2. a不在map,但b在, 肯定被别的字母映射了, false
case 3. a在, a == b continue
case 4. a在, a != b false

或者发现规律:
String 1:              A B E A C D B
index pattern:         0 1 2 0 4 5 1
String 2:              X Y I X H K Y
index pattern:         0 1 2 0 4 5 1

用两个map去存这个index, 两个map的index必须一样

 */
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for (int i = 0 ; i< s.length(); i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.containsKey(a)) {
                if(map.get(a).equals(b)) {
                    continue;
                } else {
                    return false;
                }
            } else {
                if(!map.containsValue(b)) {
                    map.put(a,b);
                } else {
                    return false;
                }  
            }
        }
        return true;       
    }
}

//方法2
public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        
        Map<Character, Integer> mapS = new HashMap<Character, Integer>();
        Map<Character, Integer> mapT = new HashMap<Character, Integer>();
        
        for (int i = 0; i < s.length(); i++) {
            int indexS = mapS.getOrDefault(s.charAt(i), -1);
            int indexT = mapT.getOrDefault(t.charAt(i), -1);
                        
            if (indexS != indexT) {
                return false;
            }
            
            mapS.put(s.charAt(i), i);
            mapT.put(t.charAt(i), i);
        }
        
        return true;
    }