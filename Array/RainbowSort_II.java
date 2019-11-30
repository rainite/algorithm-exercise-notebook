  /**
  RainbowSort基础上, 加一个颜色, 变成 0,1,2,3 4个颜色排序, 要求space O(1)
  超过3个颜色, 一次性排序就很难挪挡板了,(3个颜色的时候,挪动搜索ptr指针没法做排序,因为正好只有3个颜色所以ptr在中间那个颜色里也不需要做swap) 
  这个时候就两个两个的排, 第一次排确定最小和最大值,然后再确定次小次大,以此类推
   */
  
  public int[] rainbowSortII(int[] array) {
    // Write your solution here.
    if (array.length == 0) {
      return array;
    }
    int i = 0;
    int j = 0;
    int k = array.length - 1;
    // 第一次只排0,3
    while (j <= k) {
      if (array[j] == 0) {
        swap(array, i++, j++);
      } else if (array[j] == 3) {
        swap(array, j, k--);
      } else {
        j++;
      }
    }
    // 第二次确定1,2位置
    while (i <= k) {
      if (array[i] == 1) {
        i++;
      } else {
        swap(array, i, k--);
      }
    }
    return array；
  }
  private void swap(int[] array, int left, int right) {
    int tmp = array[left];
    array[left] = array[right];
    array[right] = tmp;
  }