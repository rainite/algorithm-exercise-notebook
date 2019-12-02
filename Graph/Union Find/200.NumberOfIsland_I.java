/**
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

思考: 直接见注释了, union find运用到这里主要是为了查当前集合个数
初始化: 把每个'1'当做一个node, parent[]存扁平化的2D index
 */
class Solution {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int row = grid.length;
    int col = grid[0].length;
    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    UnionFind uf = new UnionFind(grid);
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1') {
          //dedup
          grid[i][j] = '0';
          // 上下左右合并
          for (int[] dir : dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI >= 0 && newI < row && newJ >= 0 && newJ < col && grid[newI][newJ] == '1') {
              uf.union(i * col + j, newI * col + newJ);
            }
          }
        }
      }
    }
    return uf.count;
  }
}

class UnionFind {
  int [] parent;
  int count;
  UnionFind(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    parent = new int[row * col];
    count = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1') {
          parent[i * col + j] = i * col + j;
          count++;
        }
      }
    }
  }
  int find(int x) {
    if (x != parent[x]) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }
  void union(int x, int y) {
    int px = find(x);
    int py = find(y);
    if (px != py) {
      parent[px] = py;
      count--;
    }
  }
}