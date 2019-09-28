/*
Given a sorted array and a target value, return the index where it would be if it were inserted in order. 

Assumptions
If there are multiple elements with value same as target, we should insert the target before the first existing element.

Examples

[1,3,5,6], 5 → 2

[1,3,5,6], 2 → 1

[1,3,5,6], 7 → 4

[1,3,3,3,5,6], 3 → 1

[1,3,5,6], 0 → 0

思考:
case 1. if target in array, return array.indexOf(最左边的target)
case 2. if target not in array, return array.indexOf(第一个小于target的) + 1
 */
 
public class Solution {
  public int searchInsert(int[] array, int target) {
    // Write your solution here
    if (array == null || array.length == 0) {
      return 0;
    }
    int i = 0;
    int j = array.length - 1;
    while (i < j) {
      int mid = i + (j - i) / 2;
      if (array[mid] == target) {
        i = mid;
        j = mid;
        break;
      }
      else if (array[mid] < target) {
        i = mid + 1;
      } else {
        j = mid - 1;
      }
    }
    // 此时array[i]一定是一个<= target 的数
    while (i >= 0 && array[i] >= target) {
      i--;
    }
    return i + 1;
  }
}
