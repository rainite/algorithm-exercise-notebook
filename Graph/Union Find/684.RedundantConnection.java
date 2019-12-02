/**
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

标准union find检查连通性的题
 */
class Solution {
  public int[] findRedundantConnection(int[][] edges) {
    UnionFind uf = new UnionFind(edges);
    for (int[] e : edges) {
      if (uf.union(e[0], e[1])) {
        return e;
      }
    }
    return new int[0];
  }
  
}

class UnionFind {
  Map<Integer, Integer> map;
  UnionFind(int[][] edges) {
    map = new HashMap<>();
    for (int[] e : edges) {
      map.putIfAbsent(e[0], e[0]);
      map.putIfAbsent(e[1], e[1]);
    }
  }
  int find(int i) {
    if (map.get(i) != i) {
      int parent = find(map.get(i));
      map.put(i, parent);
    }
    return map.get(i);
  }
  boolean union(int i, int j) {
    int rootI = find(i);
    int rootJ = find(j);
    if (rootI != rootJ) {
      map.put(rootI, map.get(rootJ));
      return false;
    }
    return true;
  }
}