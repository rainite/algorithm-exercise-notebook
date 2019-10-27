/* level n 
status n
memo map<String, boolean> s 可被break与否
分析: 一个单词能否被break, 条件为
例子: abcd
break(abc) && set(d) || break(ab) && set(cd) || break(a) && set(bcd) || set(abcd)
只要有一个条件成立, 原string 就可以
*/
class Solution {
    Map<String, Boolean> map = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null) {
            return true;
        }
        Set<String> set = new HashSet<String>(wordDict);
        return helper(s, set);
    }
    private boolean helper(String s, Set<String> set) {
        // base
        if (s.length() == 0) {
            return true;
        }
        // memo check
        if (map.containsKey(s)) {
            return map.get(s);
        }
        
        for (int i = s.length() - 1; i >= 0; i--) {
            String left = s.substring(0,i);
            String right = s.substring(i);
            //短路规则, n个 status 只要找到一个就把其他的可能性短路了
            if (set.contains(right) && helper(left, set)) {
                map.put(s,true);
                return true;
            }
        }
        map.put(s, false);
        return false;
    }
}