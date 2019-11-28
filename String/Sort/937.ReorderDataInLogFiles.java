/**
You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

Return the final order of the logs.

 

Example 1:

Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

思路: 重写comparator的好题, 把题中意思翻译成程序语言
1. digits顺序不变 -> 如果是digits return 0
2. letters 按照 lexicographically顺序, 直接调用s1.compareTo(s2)
    2.1 corner case , s1 后半部 == s2 后半部 ? 判断前面的头谁大
3. letters 优先级 大于 digits
 */
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null) {
            return new String[0];
        }
        Arrays.sort(logs, new LogComparator());
        return logs;
    }
    
}

class LogComparator implements Comparator<String> {
    public int compare(String s1, String s2) {
        String[] s1Parts = s1.split(" ");
        String[] s2Parts = s2.split(" ");
        
        char s1First = s1Parts[1].charAt(0);
        char s2First = s2Parts[1].charAt(0);
        // if both string are digits, remain the sequence
        if (Character.isDigit(s1First) && Character.isDigit(s2First)) {
            return 0;
        }
        // if both string are letters, compare each other
        if (!Character.isDigit(s1First) && !Character.isDigit(s2First)) {
            String log1 = s1.substring(s1Parts[0].length() + 1);
            String log2 = s2.substring(s2Parts[0].length() + 1);
            int res = log1.compareTo(log2);
            return res != 0 ? res : s1Parts[0].compareTo(s2Parts[0]);
        }
        // letter first
        return Character.isDigit(s1First) ? 1 : -1;

    }
}