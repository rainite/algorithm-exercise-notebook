/**
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */

 class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList == null || wordList.size() == 0) {
            return res;
        }
        Set<String> wordDict = new HashSet<>(wordList);
        if (!wordDict.contains(endWord)) {
            return res;
        }
        Set<String> curLevel = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();
        boolean find = false;
        curLevel.add(beginWord);
        while (!curLevel.isEmpty() && !find) {
            wordDict.removeAll(curLevel);
            Set<String> nextLevel = new HashSet<>();
            for (String s : curLevel) {
                map.put(s, new ArrayList<>());
                char[] ch = s.toCharArray();
                for (int i = 0; i < s.length(); i++) {
                    char c = ch[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        if (c == j) {
                            continue;
                        }
                        ch[i] = j;
                        String temp = new String(ch);
                        if (!wordDict.contains(temp)) {
                            continue;
                        }
                        nextLevel.add(temp);
                        map.get(s).add(temp);
                        if (endWord.equals(temp)) {
                            find = true;
                        }
                    }
                    ch[i] = c;
                }
            }
            curLevel = nextLevel;
        }
        if (!find) {
            return res;
        }
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        helper(res, beginWord, endWord, list, map);
        return res;
    }
    public void helper(List<List<String>> res, String beginWord, String endWord, List<String> list, Map<String, List<String>> map) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (!map.containsKey(beginWord)) {
            return;
        }
        for (String s : map.get(beginWord)) {
            list.add(s);
            helper(res, s, endWord, list, map);
            list.remove(list.size() - 1);
        }
    }
}


    //distance.entrySet().stream().forEach(System.out::println);
    //map.entrySet().stream().forEach(System.out::println);



class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    if (beginWord == null || beginWord.length() == 0 || endWord == null || endWord.length() == 0) {
      return new ArrayList<>();
    }
    // data
    Set<String> set = new HashSet<>(wordList);
    Queue<String> queue = new LinkedList<>();
    Map<String, Integer> distance = new HashMap<>();
    Map<String, List<String>> predecessor = new HashMap<>();
    List<List<String>> res = new ArrayList<>();

    // corner
    if (beginWord.equals(endWord)) {
      List<String> temp = new ArrayList<>();
      temp.add(beginWord);
      res.add(temp);
    }

    // Init
    queue.add(beginWord);
    distance.put(beginWord, 0);

    // Algorithm
    while (!queue.isEmpty()) {
      String cur = queue.poll();
      if (cur.equals(endWord)) {
        break;
      }

      for (int i = 0; i < cur.length(); i++) {
        char[] array = cur.toCharArray();
        for (char c = 'a'; c <= 'z'; c++) {
          array[i] = c;
          String newCur = new String(array);
          if (set.contains(newCur)) {
            // 2种情况, generate出来可能是以前有过的'祖先', 这样会形成环, 也有可能是同层其他word generate出来的在下一层,我们只去除环的情况
            if (distance.containsKey(newCur) && distance.get(newCur) != distance.get(cur) + 1) {
              continue;
            }
            queue.offer(newCur);
            distance.put(newCur, distance.get(cur) + 1);
            if (!predecessor.containsKey(newCur)) {
              predecessor.put(newCur, new ArrayList<>());
            }
            //????
            if (predecessor.get(newCur).contains(cur)) {
              continue;
            }
            predecessor.get(newCur).add(cur);
          }
        }
      }
    }
    //distance.entrySet().stream().forEach(System.out::println);
    //predecessor.entrySet().stream().forEach(System.out::println);
    if (!predecessor.containsKey(endWord)) {
      return res;
    }
    List<String> aug =  new ArrayList<>();
    aug.add(endWord);
    restorePath(res, predecessor, aug, beginWord, endWord);
    return res;
  }

  private void restorePath(List<List<String>> res, Map<String, List<String>> predecessor, List<String> aug, String beginWord, String word) {
    //base
    if (word.equals(beginWord)) {
      List<String> temp = new ArrayList<>(aug);
      Collections.reverse(temp);
      res.add(temp);
      return;
    }

    for (String child : predecessor.get(word)) {
      aug.add(child);
      restorePath(res, predecessor, aug, beginWord, child);
      aug.remove(aug.size() - 1);
    }
  }
}