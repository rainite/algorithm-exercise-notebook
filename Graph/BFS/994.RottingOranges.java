/**
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.

多起点的BFS, 注意grid里有3种元素, Corner case判断
1. 有独立的1,没法bfs感染到的, 返回-1
2. 全是0, 返回0
 */
class Solution {
  public int orangesRotting(int[][] grid) {
    int res = 0;
    if (grid == null || grid[0].length == 0) {
      return -1;
    }
    Queue<int[]> que = new LinkedList<>();
    int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 2) {
          que.offer(new int[]{i,j});
        }
      }
    }
    while (!que.isEmpty()) {
      int level = que.size();
      for (int i = 0; i < level; i++) {
        int[] cur = que.poll();
        for (int[] dir : dirs) {
          int x = cur[0] + dir[0];
          int y = cur[1] + dir[1];
          if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
            continue;
          }
          grid[x][y] = 2;
          que.offer(new int[]{x,y});
        }
      }
      res++;
    }
    // check if there's an isolated 1
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 1) {
          return -1;
        }
      }
    } 
    // corner case if grid 全是 0
    return res == 0 ? res : res - 1;
  }
}