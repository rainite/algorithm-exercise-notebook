/**
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

基础topo题, 先建图, 再统计入度,同时要有一个count来计算是不是所有点都遍历了
 */

class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null || prerequisites.length == 0) {
      return true;
    }
    Map<Integer, List<Integer>> map = new HashMap<>();
    Map<Integer, Integer> indegree = new HashMap<>();
    //build graph, caculate indgree
    for (int[] pair : prerequisites) {
      // create node
      map.putIfAbsent(pair[1], new ArrayList<>());
      map.putIfAbsent(pair[0], new ArrayList<>());
      indegree.putIfAbsent(pair[0], 0);
      indegree.putIfAbsent(pair[1], 0);
      // create edge (value)
      map.get(pair[1]).add(pair[0]);
      indegree.put(pair[0], indegree.get(pair[0]) + 1);
    }
    Queue<Integer> que = new LinkedList<>();
    int count = 0;
    // init queue
    for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
      if (entry.getValue() == 0) {
        que.add(entry.getKey());
        count++;
      }
    }
    
    //topo
    while (!que.isEmpty()) {
      Integer cur = que.poll();
      if (--numCourses == 0) {
        return true;
      }
      // indegree - 1
      for (Integer course : map.get(cur)) {
        Integer in = indegree.get(course);
        if (--in == 0) {
          que.add(course);
          count++;
        }
        indegree.put(course, in);
      }
    }
    return count == indegree.size() ? true : false;    
  }
}