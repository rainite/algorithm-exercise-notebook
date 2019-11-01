/**
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

/*
Clarification:
input: String of beginWord, endWord; List<String>wordList
output: int steps;
string only contains a-z, should be valid
if no answer return 0

data:
FIFO queue
HashMap<String, Integer> word -> step
HashSet<String> wordList

Init:
queue(beginWord)
Map<beginWord, 1>

algorithm:
queue(begin)
expand queue
for word in queue:
    for len in len(word):
        loop from a - z, check if in set
            case1: in set, put in map, get step from map.
            case2: no, continue
termination:
case1: found endWord. return steps
case2: queue.isEmpty, -1
*/

class Solution {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0) {
      return -1;
    }
    // Data structure
    Set<String> set = new HashSet<>(wordList);
    Map<String, Integer> map = new HashMap<>();
    Queue<String> queue = new LinkedList<>();

    //Init
    queue.add(beginWord);
    map.put(beginWord, 1);

    //Algorithm
    while (!queue.isEmpty()) {
      String cur = queue.poll();
      if (cur.equals(endWord)) {
        return map.get(endWord);
      }      
      for (int i = 0; i < cur.length(); i++) {
        char[] array = cur.toCharArray();
        for (char c = 'a' ; c <= 'z'; c++) {
          array[i] = c;
          String newCur = new String(array);
          if(map.containsKey(newCur)) {
            continue;
          }
          if (set.contains(newCur)) {
            map.put(newCur, map.get(cur) + 1);
            queue.offer(newCur);
          }
        }
      }
    }
    return 0;
  }
}