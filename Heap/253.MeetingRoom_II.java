/**
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

以开始时间sort, 再创建一个时间记录系统, 去统计总共有多少重合的时间段
主要是需要追踪结束时间, 用min_heap排序时间的思想
 */
class Solution {
  public int minMeetingRooms(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }
    Arrays.sort(intervals, (a,b) -> (a[0] - b[0]));
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int i = 0; i < intervals.length; i++) {
      if (minHeap.isEmpty()) {
        minHeap.offer(intervals[i][1]);
      } else {
        if (intervals[i][0] >= minHeap.peek()) {
          minHeap.poll();
        } 
        minHeap.offer(intervals[i][1]);
        
      }
    }
    return minHeap.size();
  }
}