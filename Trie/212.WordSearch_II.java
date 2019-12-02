/**
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]

标准trie搜索, 注意单词是存在edge上的(不是存在TrieNode里)
dfs搜索用前序遍历来sanity check, 去重路径
然后检查下一层的边(map.containsKey())
添加单词条件: 通过下一层的边进入下一层node后, 查看这个node的isWord是不是true
这里有空间可以优化:
1. visited可直接改board为'#', 省visited
2. isWord可改为value = string, 这样省sb
 */

class TrieNode{
  Map<Character, TrieNode> map = new HashMap<>();
  boolean isWord = false;

  public void insert(String word) {
    TrieNode cur = this;
    for (int i = 0; i < word.length(); i++) {
      cur.map.putIfAbsent(word.charAt(i), new TrieNode());
      cur = cur.map.get(word.charAt(i));
    }
    cur.isWord = true;
  }
}

class Solution {
  public List<String> findWords(char[][] board, String[] words) {
    TrieNode root = new TrieNode();
    List<String> res = new ArrayList<>();
    for (String s : words) {
      root.insert(s);
    }
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        dfs(root, new StringBuilder(), res, board, new boolean[board.length][board[0].length], i, j);
      }
    }
    return res;
  }
  private void dfs(TrieNode curNode, StringBuilder sb,
                   List<String> res, char[][] board,
                   boolean[][] visited, int i, int j) {

    if (i >= board.length || i < 0 || j >= board[0].length || j < 0 ||
            visited[i][j] || !curNode.map.containsKey(board[i][j])) {
      return;
    }

    visited[i][j] = true;
    sb.append(board[i][j]);
    TrieNode nextNode = curNode.map.get(board[i][j]);

    if (nextNode.isWord == true) {
      res.add(sb.toString());
      //dedup
      nextNode.isWord = false;
    }

    dfs(nextNode, sb, res, board, visited, i + 1, j);
    dfs(nextNode, sb, res, board, visited, i - 1, j);
    dfs(nextNode, sb, res, board, visited, i, j + 1);
    dfs(nextNode, sb, res, board, visited, i, j - 1);

    visited[i][j] = false;
    sb.deleteCharAt(sb.length() - 1);
  }
}