public class RainbowSort {
  public int[] rainbowSort(int[] array) {
    // Write your solution here
    if(array == null || array.length == 1) return array;
    /**
    挡板思想 3个pointer, 4块区域
    [0,i): i左边的全是 -1
    [i,j): j左边到i的全是0
    [i,k]: 未探索区域
    [k,n-1]: k右边的全是1

    所以我们主要挪的是j这个pointer
    case1, if input[j] == -1, swap(i,j) i++
    case2, if input[j] == 0, j++
    case3, if input[j] == 1, swap(j,k) k--
    **/ 
    int i,j,k;
    i = j = 0; k = array.length - 1;
    while(j <= k){
      if(j <= k && array[j] == -1) {
        swap(array,i,j);
        i++;j++;
      }
      else if(j <= k && array[j] == 0) j++;
      else {
        swap(array,j,k);
        k--;
      }
    }
    return array;
  }

  private void swap(int[] array, int a, int b){
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }
}
