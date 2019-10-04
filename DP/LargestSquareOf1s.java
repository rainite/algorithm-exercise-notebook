/**
Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

Assumptions

The given matrix is not null and guaranteed to be of size N * N, N >= 0
Examples

{ {0, 0, 0, 0},

  {1, 1, 1, 1},

  {0, 1, 1, 1},

  {1, 0, 1, 1}}

the largest square of 1s has length of 2

分析: 要确定向右下扩张一个顶点能否构成更大的正方形, 是基于左, 左上, 上 3个方向是不是正方形. 基于此可以得到
物理意义: M[i][j] 表示 从坐标x轴从0 ~ i, y轴0 ~ j 能围成的正方形的最长边长
induction rule:
    if A[i][j] == 0: M[i][j] = 0; 顶点是0没法构成正方形
    else:
        基于左,左上,上三个方向
        M[i][j] = min(M[i - 1][j], M[i - 1][j - 1], M[i][j - 1]) + 1

注意, 这里为了方便计算我们脱战左右都拓展一个数
int[][] dp =  new int[matrix.length + 1][matrix[0].length + 1];
从 1, 1 开始匹配 A[0][0] 注意映射关系
或者
不拓展,注意处理i = 0 和 j = 0 时,
 if i == 0 or j == 0: dp[i][j] == 1 if A[i][j] == 1 else 0
 */


public class Solution {
  public int largest(int[][] matrix) {
    // Write your solution here
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int globalMax = 0;
    int[][] dp =  new int[matrix.length + 1][matrix[0].length + 1];
    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        if (matrix[i - 1][j - 1] == 0) {
          dp[i][j] = 0;
        } else {
          dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
          globalMax = Math.max(dp[i][j], globalMax);
        }
      }
    }
    return globalMax;
  }
}
