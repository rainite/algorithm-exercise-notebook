/*
Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:

Input: [[1,1],[-1,1]]
Output: true
Example 2:

Input: [[1,1],[-1,-1]]
Output: false

思路： 先分析
1. 对称轴两边的点， x坐标离对称轴相等， y相等
2. 怎么判断相等？ 所有点中 x坐标的 (max - min) / 2 + min => (max + min) / 2 是对称轴坐标。 但是这里可能出现小数
3. 不转double怎么做， 直接用和， min + max，　对于任意对称与轴的点i，j，必有min + max == i + j 这里可以用2sum思想
4. 如果这里排序这个数组，怎么处理y的顺序也是个问题，如果多个x相同但y不相同的点， 没办法直接排序， 所以干脆就用个set就好了
5. 也有sort和双指针去重的做法， 但是不好写排序规则
*/
class Solution {
  public boolean isReflected(int[][] points) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    HashSet<String> set = new HashSet<>();
    for(int[] p:points){
        max = Math.max(max,p[0]);
        min = Math.min(min,p[0]);
        String str = p[0] + "," + p[1];
        set.add(str);
    }
    int sum = max+min;
    for(int[] p:points){
        //int[] arr = {sum-p[0],p[1]};
        String str = (sum-p[0]) + "," + p[1];
        if( !set.contains(str))
            return false;
        
    }
    return true;
  }
}