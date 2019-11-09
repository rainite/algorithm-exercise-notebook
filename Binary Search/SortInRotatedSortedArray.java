/*
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * 

 思路：这题我们思路为确定值在哪个范围, 找到连续的可以做判断的数组去做文章, 因为连续的数组才好做判断
 并且因为是找范围, 那么情况为确定target在或不在[left,right]这个开区间内, 用 left + 1 < right
 1. 值在mid右边的情况:
    左连续, 且值不在左边, within函数通过开区间判断
    右连续, 且值就在右边
    left = mid
 2. else:
    right = mid

 */

// @lc code=start
class Solution {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    
    int i = 0;
    int j = nums.length - 1;
    while (i + 1 < j) {
      int mid = i + (j - i) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[i] < nums[mid] && !within(target, nums[i], nums[mid]) ||
                nums[mid] < nums[j] && within(target, nums[mid], nums[j])) {
        i = mid + 1;
      } else {
        j = mid - 1;
      }
    }
    if (nums[i] != target && nums[j] != target) {
      return -1;
    }
    return nums[i] == target ? i : j;
  }
  private boolean within(int t, int x, int y) {
    // 这里若严格根据物理意义, mid已经被看过了, 那下一个开区间应该是不包括的mid的,但是x,y都有可能是mid, 方便起见就直接取开区间了
    return t >= x && t <= y;
  }
}

// 严格物理意义版本:
class Solution {
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    
    int i = 0;
    int j = nums.length - 1;
    while (i + 1 < j) {
      int mid = i + (j - i) / 2;
      if (nums[i] < nums[mid] && !within(target, nums[i], nums[mid]) ||
                nums[mid] < nums[j] && within(target, nums[mid], nums[j])) {
        i = mid;
      } else {
        j = mid;
      }
    }
    if (nums[i] != target && nums[j] != target) {
      return -1;
    }
    return nums[i] == target ? i : j;
  }
  private boolean within(int t, int x, int y) {
    return t >= x && t <= y;
  }
}