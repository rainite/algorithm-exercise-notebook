/**
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0

在union find里多实现一个add 点的操作, 先初始化点的parent, 再4个方向查并集
 */
class Solution {
  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    UnionFind uf = new UnionFind(m, n);
    List<Integer> res = new ArrayList<>();
    if (positions == null || positions.length == 0) {
      return res;
    }
    int row = positions.length;
    int col = positions[0].length;
    for (int[] pos : positions) {
      uf.add(pos[0], pos[1]);
      res.add(uf.count);
    }
    return res;
  }
}
class UnionFind {
  int[] parent;
  int[][] grid;
  int count;
  UnionFind(int m, int n) {
    parent = new int[n * m];
    grid = new int[m][n];
    count = 0;
  }
  int find(int i) {
    if (i != parent[i]) {
      parent[i] = find(parent[i]);
    }
    return parent[i];
  }
  void union(int i, int j) {
    int rootI = find(i);
    int rootJ = find(j);
    if (rootI != rootJ) {
      parent[rootI] = rootJ;
      count--;
    }
  }
  void add(int i, int j) {
    int row = grid.length;
    int col = grid[0].length;
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    if (grid[i][j] == 0) {
      grid[i][j] = 1;
      parent[i * col + j] = i * col + j;
      count++;
      for (int[] dir : dirs) {
        int newI = i + dir[0];
        int newJ = j + dir[1];
        if (newI >= 0 && newI < row && newJ >= 0 && newJ < col && grid[newI][newJ] == 1) {
          union(i * col + j, newI * col + newJ);
        }
      }
    }
  }
}