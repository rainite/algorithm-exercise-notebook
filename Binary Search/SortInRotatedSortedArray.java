/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (33.19%)
 * Likes:    3001
 * Dislikes: 360
 * Total Accepted:    487.8K
 * Total Submissions: 1.5M
 * Testcase Example:  '[4,5,6,7,0,1,2]\n0'
 *
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

 思路： 慢慢分析case，同样先找中点判断大小，　找确定数，　退出条件位i == j 所以用　i < j
 case1： 中点小于target
    case1.1 中点左边连续　－>　值在右边
    case1.2 else(右边连续左边有断点)
        case1.2.1　右边最大值(j)小于target -> 值在左边
        case1.1.2　else －> 值在右边
case2: else(中点大于target)
    case2.1　中点右边连续　－>　值在左边
    else
        case 2.2.1 左边最大值(i)大于target -> 值在右边
        else ->　值在左边
 */

// @lc code=start
class Solution {
    public int search(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int i = 0, j = array.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                if (array[i] < array[mid]) {
                    i = mid + 1;
                } else {
                    if (array[j] >= target) {
                        i = mid + 1;
                    } else {
                        j = mid - 1;
                    }
                }
            } else {
                if (array[j] > array[mid]) {
                    j = mid - 1;
                } else {
                    if (array[i] >  target) {
                        i = mid + 1;
                    } else {
                        j = mid - 1;
                    }
                }
            }
        }
        return array[i] == target ? i : -1;
    }
}
// @lc code=end

you