import java.util.Arrays;

public class QuickSort {
    public int[] quickSort(int[] array) {
        // Write your solution here
        if(array == null || array.length < 2) return array;
        quickSort(array,0,array.length -1);
        return array;
    }    

    private void quickSort(int[] array, int left, int right){
      // base case
      // 此时 'right' 是可以出现跑过 left 的情况的，看下面递归，如果pivotPos = left 时
      if(left >= right) return;
      // 我们是可以在一个subarray里面做partition的， 左右边界这里可以灵活
      // partition 即为 确定一个随机元素的准确位置 的算法
      int pivotPos = partition(array,left,right);
      // 对于已经确定准确位置元素的左边和右边，都做同样的事排好序，就完事
      quickSort(array,left,pivotPos - 1);
      quickSort(array,pivotPos + 1, right);
    }


    private int partition(int[] array,int left, int right){
      // 注意起点是left,或者说offset是left
      int pivotIndex = left + (int)(Math.random() * (right - left + 1));
      // 这里很有必要,后面会多次比较pivot的值,如果还是用index, 在swap后脑子会晕,刻舟求剑的故事
      int pivot = array[pivotIndex];
      // 交换pivot
      swap(array,pivotIndex,right);
      // 起点是left！终点是right - 1！
      int i = left; int j = right - 1;
      /**
      挡板思想 2个pointer, 3块区域
      [0,i): i左边的全是 小于pivot的
      [i,j]: 未探索区域
      (j,n-1]: j右边全是 大于等于 pivot的

      所以我们主要挪的是i这个pointer
      case1, if input[i] < pivot, i++
      case2, if input[i] >= pivot, swap(array,i,j); j--;
      while(i <= j){
          if(array[i] < pivot) i++;
          else{
            swap(array,i,j);
            j--;
          }
      }
      **/
      // i 与 j 相遇并错过1位 为退出条件，这样才检查了i==j时，array[i]与pivot的大小
      // 因为就2个pointer， 可以这么玩，如果是3个以上，就只能按照挡板法来分析区域和挪动的pointer了
      while(i <= j){
          while(i <= j && array[i] < pivot) i++;
          while(i <= j && array[j] >= pivot) j--;
          //如果j 已经跑到-1了， 就不用swap了
          if(i <= j)
            swap(array,i,j);
      }
      // 此时 i 左边的全是小于pivot，j右边全是大于pivot，i是j右边第一个，所以交换i与pivot很稳
      swap(array,i,right);
      return i;
    }

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}