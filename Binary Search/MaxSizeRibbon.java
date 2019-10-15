/*
Maximum size of ribbon
Given a list representing the length of ribbon, and the target number "k" parts of ribbon.
we want to cut ribbon into k parts with the same size, at the same time we want the maximum size.
Ex.
Input: A = [1, 2, 3, 4, 9], k = 5
Output: 3

Explanation:‍‍‌‌‌‍‍‍‍‌‍‍‌‍‌‌‌‍‍
if size = 1, then we have 19 parts
if seize = 2, then we have 8 parts
if size = 3, then we have 5 parts
if size = 4, then we have 3 parts, which is not enough.
So return the max size = 3.

*/

class Solution {
  public static void main(String[] args) {
    System.out.println(ribbon(new int[]{1,2,3,4,9}, 5));
  }
  
  private static int ribbon(int[] arr, int k) {
    int hi = 0;
    for (int i : arr) hi = Math.max(hi, i);
    int lo = 1;
    int res = 0;
    while (lo <= hi) {
      int mid = (lo + hi)/2;
      int curr = 0;
      for (int i : arr) curr += i/mid;
      if (curr >= k) {
        res = Math.max(res, mid);
        lo = mid + 1;
      } else hi = mid - 1;

    }
    return res;
  }
}
