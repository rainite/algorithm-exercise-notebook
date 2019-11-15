/**
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

思考: 就是个k问题， 合并k个时间段， 这里我们最straight forward思路, iterative reduction
 */

class Solution {
  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return intervals;
    }
    int left = 0;
    int right = 0;
    List<int[]> res = new ArrayList<>();
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    for (int i = 0; i < intervals.length; i++) {
      if (i == 0) {
        left = intervals[i][0];
        right = intervals[i][1];
      } else {
        if (intervals[i][0] <= right) {
          right = Math.max(right, intervals[i][1]);
        } else {
          res.add(new int[]{left, right});
          left = intervals[i][0];
          right = intervals[i][1];
        }
      }
    }
    //把最后更新的值加进去
    res.add(new int[]{left, right});
    return res.toArray(new int[][]{});  
  }
}