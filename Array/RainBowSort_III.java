/**
Given an array of balls with k different colors denoted by numbers 1- k, sort the balls.

Examples

k=1, {1} is sorted to {1}
k=3, {1, 3, 2, 1, 2} is sorted to {1, 1, 2, 2, 3}
k=5, {3, 1, 5, 5, 1, 4, 2} is sorted to {1, 1, 2, 3, 4, 5, 5}
Assumptions

The input array is not null.
k is guaranteed to be >= 1.
k << logn.

RainbowSort 完整版, 空间退化到o(logK), 时间O(nlogK), 还是比直接排序优化的
 */

public class Solution {
  public int[] rainbowSortIII(int[] colors, int k) {
    if (colors == null || colors.length == 0) {
      return colors;
    }
    rainbowSort(colors, 0, colors.length - 1, 1, k);
    return colors;
  }

  public void rainbowSort(int[] colors, int leftBound, int rightBound, int colorFrom, int colorTo) {
    if (colorFrom == colorTo || leftBound >= rightBound) {
      return;
    }
    // 快速排序思想, 找颜色的pivot, 分治排序
    // 一次只排3个颜色, 小于mid, 等于mid, 大于mid
    int colorMid = colorFrom + (colorTo - colorFrom) / 2;
    int left = leftBound, right = rightBound, ptr = leftBound;
    while (ptr <= right) {
      if (colors[ptr] < colorMid) {
        swap(colors, left++, ptr++);
      } else if (colors[ptr] == colorMid) {
        ptr++;
      } else {
        swap(colors, ptr, right--);
      }
    }
    // 注意上面的left和right的定义，指的是不包括left的左边和不包括right的右边，而bound的定义为包括这个数
    rainbowSort(colors, leftBound, left - 1, colorFrom, colorMid - 1);
    rainbowSort(colors, right + 1, rightBound, colorMid + 1, colorTo);
  }
  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}