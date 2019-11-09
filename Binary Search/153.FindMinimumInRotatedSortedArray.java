/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0

思路: 断点就是要找的值, 那么就多判断一个有没有断点
 */

class Solution {
  public int findMin(int[] array) {
   if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0, right = array.length - 1;
    // 断点就是答案
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      //如果连续, 答案就是left
      if (array[left] < array[mid] && array[mid] < array[right]) {
        return array[left];
      } else if (array[left] < array[mid]){
        left = mid;
      } else {
        right = mid;
      }
    }
    return array[left] < array[right] ? array[left] : array[right];
  }
}