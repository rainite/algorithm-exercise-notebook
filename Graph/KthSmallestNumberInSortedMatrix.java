/*
Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

Assumptions

the matrix is not null, N > 0 and M > 0
K > 0 and K <= N * M
Examples

{ {1,  3,  5,  7},

  {2,  4,  8,  9},

  {3,  5, 11, 15},

  {6,  8, 13, 18} }

the 5th smallest number is 4
the 8th smallest number is 6

思路: 可以理解成从0，0出发，能到达的第K个最短路径的node在哪，matrix里的值就等于权重，
于是用Best First Search， Dijkstra 来做
BFS 3要素，1.什么queue，装什么类型， 此题因为node的neighbors和坐标x,y和value都有关，所以存一个class Cell
2. 如何遍历，即direction定义，此题只有往右和下走有意义，那么二维数组存2个基坐标搞定
3. 处理visited，最开始我想用Set来存新建的Cell，想多了，直接boolean[][]对应matrix，遍历过设为true，visited不需要知道具体的cell
4. terminate，因为是找第K个，那么就是poll的第k个就是，注意边界处理，可能K太大了找不到
 */

public class Solution {

  public class Cell {
    int x;
    int y;
    int value;

    public Cell(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }
  public int kthSmallest(int[][] matrix, int k) {
    // assume matrix and k are both valid and meaningful
    int row = matrix.length;
    int column = matrix[0].length;
    PriorityQueue<Cell> pq = new PriorityQueue<>(k * 2, (x,y) -> x.value - y.value);
    boolean [][] visited = new boolean[row][column];
    // only 2 directions (down or right) are meaningful at this point
    int[][] directions = {{1,0},{0,1}};
    pq.add(new Cell(0, 0, matrix[0][0]));
    while (!pq.isEmpty() && k > 0) {
      Cell cur = pq.remove();
      if (--k == 0) {
        return cur.value;
      }
      // add neighbors
      for (int[] i : directions) {
        int candiX = cur.x + i[0];
        int candiY = cur.y + i[1];
        if (candiX < row && candiY < column && !visited[candiX][candiY]) {
          visited[candiX][candiY] = true;
          pq.add(new Cell(candiX,candiY,matrix[candiX][candiY]));
        }
      }
    }
    return -1;
  }
}
