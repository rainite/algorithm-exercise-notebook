/**
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

初级DP: 因为当前格子能蓄水的高度,等于min(左边最高的柱子高度, 右边最高柱子高度)
所以分别求出每个格子的两个最值就好
M[i] = min(dpleft[i], dpright[i]) - A[i]

Time: O(n)
Space: O(n)
 */
class Solution {
  public int trap(int[] height) {
    int[] dpleft = new int[height.length];
    int[] dpright = new int[height.length];
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < height.length; i++) {
      max = Math.max(height[i], max);
      dpleft[i] = max;
    }
    max = Integer.MIN_VALUE;
    for (int i = height.length - 1; i >= 0; i--) {
      max = Math.max(height[i], max);
      dpright[i] = max;
    }
    int res = 0;
    for (int i = 0; i < height.length; i++) {
      res += Math.min(dpleft[i], dpright[i]) - height[i];
    }
    return res;
  }
}


/**
高级dp: 有没有办法优化空间? 
利用单调性, 观察上面生成的dp, leftMax是单调增, rightMax是单调减,所以有
任意位置的<left,right>中, left一定>= leftMax, right一定<= rightMax, 那么
从数组两端更新这个最值,最两端可以确定最大左和最大右
如果leftMax < rightMax, 可以确定left位置蓄水量只能是leftMax - A[left], 注意上面计算蓄水量有个取min的操作
else, 可以确定right位置蓄水量只能是rightMax - A[right], 同样注意取了min
 */

class Solution {
  public int trap(int[] height) {
    int leftMax = 0, rightMax = 0;
    int left = 0, right = height.length - 1;
    int res = 0;
    while (left <= right) {
      leftMax = Math.max(height[left], leftMax);
      rightMax = Math.max(height[right], rightMax);
      if (leftMax < rightMax) {
        res += leftMax - height[left];
        left++;
      } else {
        res += rightMax - height[right];
        right--;
      }
    }
    return res;
  }
}