/**
Given a 2D board and a word, find if the word exists in the grid.The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Input: board = [

                           [“ABCE”],

                           [“SFCS”],

                           [“ADEE”]

                       ]

Output: Word = “ABCCED”   return true

            Word = “SEE”      return true

            Word = “ABCB”      return false

 思路: 遍历图, 查找整个单词, 用DFS, 和island那题类似
 level: len(word)
 status: 4个方向
 这里注意visited, visited一定要跟一个dfs路径走, 意思是说出了一个dfs就要把visited还原!

 */
class Solution {
  public boolean isWord(char[][] board, String word) {
    if (board == null || board.length == 0) {
      return false;
    }
    boolean[][]visited = new boolean[board.length][board[0].length];
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board[i].length; j++){
        if((word.charAt(0) == board[i][j]) && search(board, word, 0, i, j, visited)){
          return true;
        }
      }
    }
    return false;
  }

  public boolean search(char[][] board, String word, int index, int i, int j, boolean[][] visited){
    if(index == word.length()){
      return true;
    }
    if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]){
      return false;
    }
    // 吃
    visited[i][j] = true;

    if (search(board, word, index + 1, i - 1, j, visited) ||
        search(board, word, index + 1, i + 1, j, visited) ||
        search(board, word, index + 1, i, j - 1, visited) ||
        search(board, word, index + 1, i, j + 1, visited)){
        return true;
    }
    // 吐, 还原DFS状态
    visited[i][j] = false;
    return false;
  }
}