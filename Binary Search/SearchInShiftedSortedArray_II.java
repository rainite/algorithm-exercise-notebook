/**
Given a target integer T and an integer array A, A is sorted in ascending order first, then shifted by an arbitrary number of positions.

For Example, A = {3, 4, 5, 1, 2} (shifted left by 2 positions). Find the index i such that A[i] == T or return -1 if there is no such index.

Assumptions

There could be duplicate elements in the array.
Examples

A = {3, 4, 5, 1, 2}, T = 4, return 1
A = {3, 3, 3, 1, 3}, T = 1, return 3
A = {3, 1, 3, 3, 3}, T = 1, return 1
​Corner Cases

What if A is null or A is of zero length? We should return -1 in this case.

思路: 有重复版本的rotate array, 注意特殊的case: 1111211111111这种, 没法判断左右连续性, 
所以先拿左右与中点判断下, 如果相等,这时候没法判断, 就直接left++ 或 right--
完全能判断后, 再用二分搜索
worest case: O(n)
 */
public class Solution {
  public int search(int[] array, int target) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0, right = array.length - 1;
    while (left + 1 < right) {
      // 排除不能判断的情况
      int mid = left + (right - left) / 2;
      if (array[left] == array[mid]) {
        left++;
        continue;
      }
      if (array[mid] == array[right]) {
        right--;
        continue;
      }

      if (array[left] < array[mid] && within(target, array[left] , array[mid]) ||
        array[right] > array[mid] && !within(target, array[mid], array[right])) {
          right = mid;
      } else {
        left = mid;
      }
    }
    if (array[left] != target && array[right] != target) {
      return -1;
    }
    return array[left] == target ? left : right;
  }
  private boolean within(int t, int x, int y) {
    return t >= x && t <= y;
  }
}
