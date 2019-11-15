/*

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

思考:
第i位置上的值 = (1 ~ i-1的积) * (i+1 ~ n的积)， 思路就是分别把两边的求出来 
*/
class Solution {
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    int [] res = new int[nums.length];
    // 构建1 ~ i-1的积
    for (int i = 0; i < nums.length; i++) {
      if (i == 0) {
        res[i] = 1;
      } else {
        res[i] = res[i - 1] * nums[i - 1];
      }
    }
    // 构造i + 1 ~ n的积顺便把答案写出来
    int runningProduct = 1;
    for (int i = nums.length - 1; i >= 0; i--) {
      res[i] = res[i] * runningProduct;
      runningProduct *= nums[i]; 
    }
    return res;
  }
}