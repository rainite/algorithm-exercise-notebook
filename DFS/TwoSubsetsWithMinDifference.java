/*
Given a set of n integers, divide the set in two subsets of n/2 sizes each such that the difference of the sum of two subsets is as minimum as possible.

Return the minimum difference(absolute value).

Assumptions:

The given integer array is not null and it has length of >= 2.
Examples:

{1, 3, 2} can be divided into {1, 2} and {3}, the minimum difference is 0

思路: 
level: n层
status: 2, 每一层只需要考虑当前数放Aset还是Bset
这里因为不需要把set求出来, 所以空间优化下直接用个数来记就行
*/
public class Solution {
  private int min = Integer.MAX_VALUE;
  public int minDifference(int[] array) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return min;
    }
    helper(array, 0, 0, 0, 0, 0);
    return min;
  }
  private void helper(int[] array, int asum, int bsum, int asize, int bsize, int index) {
    if (index == array.length ) {
      if (Math.abs(asize - bsize) > 1) return;
      min = Math.min(min, Math.abs(asum - bsum));
      return;
    }
    helper(array, asum + array[index], bsum, asize + 1, bsize, index + 1);
    helper(array, asum, bsum + array[index], asize, bsize + 1, index + 1);
  }
}
