/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

 

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

Output: 2
思路:
n for len(wall), m for len(wall[0]), l is the total length of the bricks
1. get length in total
2. for 1 to length - 1, caculate the bricks for each row, record the max
Time: l * n * m
-----------------------
optimize:

prefix sum, use the prefix sum as the key. find the maximum frequency prefix, and cut there.
Time: n * m
*/
class Solution {
  public int leastBricks(List<List<Integer>> wall) {
    if (wall == null || wall.size() == 0) {
      return 0;
    }
    int maxPrefixCount = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (List<Integer> list : wall) {
      int prefix = 0;
      // 注意要是倒数第二个, 取到倒数第一那prefix全都一样了, 等于在末尾切一刀
      for (int i = 0; i < list.size() - 1; i++) {
        prefix += list.get(i);
        map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        maxPrefixCount = Math.max(maxPrefixCount, map.get(prefix));
      }
    }
    return wall.size() - maxPrefixCount;
  }
}