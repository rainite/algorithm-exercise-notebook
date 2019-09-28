/**
Given a target integer T and an integer array A sorted in ascending order, find the index of the smallest element in A that is larger than T or return -1 if there is no such index.



Assumptions

There can be duplicate elements in the array.

Examples

A = {1, 2, 3}, T = 1, return 1

A = {1, 2, 3}, T = 3, return -1

A = {1, 2, 2, 2, 3}, T = 1, return 1

Corner Cases

What if A is null or A of zero length? We should return -1 in this case.
思考:
此题因为可能出现target不在array中,所以得求一个范围,这里l,r要求在相邻的index时退出,那么确定while里是l + 1 < r,
再分析,范围是要找到第一个大于target的数,mid是可能在答案位置的,所以这里挪动l,r指针不能mid +/- 1
现在搜索,目标把l指针挪到最后一个target位置
出循环后,判断3种case
1. target <= r, 有可能到达array右边界, 返回-1
2. target 在[l,r) 中间,返回 r
3. target < l , 有可能到达array左边界, 返回 l
 */
public class Solution {
  public int smallestElementLargerThanTarget(int[] array, int target) {
    // Write your solution here
    if(array == null || array.length == 0) return -1;
    int l = 0; int r = array.length - 1;
    while(l + 1 < r){
      int mid = (l + r) / 2;
      if(array[mid] <= target) l = mid;
      else r = mid;
    }
    if(array[r] <= target) return -1;
    if(array[l] == target) return r;
    return array[l] < target ? r : l;
  }
}
