/**
Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

Assumptions

A is not null
K is guranteed to be >= 0 and K is guranteed to be <= A.length
Return

A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T. 
Examples

A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}

思路: bs找到最接近target的值, 用范围[left,right]确定, cornercase为如果i == j, 那么要把i,j错开才好操作
注意这个定义, 决定了i指针找到了target也没法挪动, 所以如果想要把定义下成: 找到最接近target切不大于target的值, 没法做
 */

public class Solution {
  public int[] kClosest(int[] array, int target, int k) {
    // Write your solution here
    if(array == null || array.length == 0 || k == 0)
      return new int[k];
    if (k > array.length) return array;
    int i = 0;
    int j = array.length - 1;

    // 找一个最接近target且不大于target的值
    while(i + 1 < j) {
      int mid = i + (j - i) / 2;
      if(array[mid] <= target) i = mid;
      else j = mid - 1;
    }
    // 错开
    if (i == j) {
      j = j + 1;
    }
    int index = 0;
    int[] res = new int[k];
    while(index < k && i >= 0 && j < array.length){
      // 想清楚， 距离
      if(Math.abs(array[i] - target) < Math.abs(array[j] - target)) res[index++] = array[i--];
      else res[index++] = array[j++];
    }
    while(index < k && i >= 0) res[index++] = array[i--];
    while(index < k && j < array.length) res[index++] = array[j++];
    return res;
  }
}
