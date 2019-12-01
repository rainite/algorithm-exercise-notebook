/**
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

k个的版本就站k-1个党派, 用map来做, 先火并照出存活的党, 再扫一遍统计个数
 */
class Solution {
  public List<Integer> majorityElement(int[] nums) {
    List<Integer> res = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      return res;
    }
    int a = nums[0];
    int b = nums[0];
    int cntA = 0;
    int cntB = 0;  
    // 这里else if 用的很精髓, 其实条件是由上到下来判断的, 并不是一个并行关系
    for (int i = 0; i < nums.length; i++) {
      if (a == nums[i]) {
        cntA++;
      } else if (b == nums[i]) {
        cntB++;
      } else if (cntA == 0) {
        a = nums[i];
        cntA++;
      } else if (cntB == 0){
        b = nums[i];
        cntB++;
      } else {
      cntA--; cntB--;
      }      
    }
    cntA = 0; cntB = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == a) {
        cntA++;
      } else if (b == nums[i]) {
        cntB++;
      }
    }
    if (cntA > nums.length / 3) {
      res.add(a);
    }
    if (cntB > nums.length / 3) {
      res.add(b);
    }
    return res;
  }
}