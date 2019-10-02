/*
Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.

Assumptions

The given array is not null and has length of at least 1.
Examples

{2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5

{-2, -1, -3}, the largest subarray sum is -1
dp 为一个数globalMax，物理意义　[0,i]之间最大的subarray和
递推公式：globalMax = max(globalMax, 
                        array[i] if curMax + array[i]  < array[i] else curMax + array[i])
*/
public class Solution {
  public int largestSum(int[] array) {
    // Write your solution here
    int globalMax = Integer.MIN_VALUE;
    int curMax = 0;
    for (int i = 0; i < array.length; i++) {
      if (i == 0 || curMax + array[i] < array[i]) {
        curMax = array[i];
      } else {
        curMax += array[i];
      }
      globalMax = Math.max(globalMax, curMax);
    }
    return globalMax;
  }
}
