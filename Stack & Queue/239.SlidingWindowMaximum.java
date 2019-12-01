/**
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note:
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

思路: 用一个单调递减deque, deque头即为当前window最大值,更新这个deque分别有2个case
case1: 新进入window的值比deque最后的值大, 即"又大又新", while 最左小于当前 pop from right, 最后 push new to right
case2: 当前最大的数从window左边退出了, 那么while deque里index小于现在left的index的, 都会过期,全部pop from left, 直到找到一个没过期的
deque.peakFirst()就是当前最大值, 用于更新res[]

细节: 整个题的操作逻辑难点在于如何维护deque的物理意义, 可以模块化为remove 和 add, 这里res class 可有可无, deque里只存index也是可以的
多存一个value操作起来方便点
 */

class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return nums;
    }
    Deque<res> deque = new LinkedList<>();
    int[] res = new int[nums.length - k + 1];
    int left = 0;
    int right = 0;
    while (right - left < k) {
      update(deque, right, nums[right++]);
    }
    res[left] = deque.peekFirst().value;
    while (right < nums.length) {
      remove(deque, left, nums[left++]);
      update(deque, right, nums[right++]);
      res[left] = deque.peekFirst().value;
    }
    return res;
  }
  private void update(Deque<res> deque, int index, int value) {
    while (!deque.isEmpty() && deque.peekLast().value < value) {
      deque.removeLast();
    }
    deque.addLast(new res(index, value));
    return;
  }
  private void remove(Deque<res> deque, int index, int value) {
    if (value < deque.peekFirst().value) {
      return;
    } else {
      while (!deque.isEmpty() && deque.peekFirst().index <= index) {
        deque.removeFirst();
      }
    }
  }
}
class res {
  int index;
  int value;
  res(int index, int value) {
    this.index = index;
    this.value = value;
  }
}