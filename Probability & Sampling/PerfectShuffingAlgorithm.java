/**
Given an array of integers (without any duplicates), shuffle the array such that all permutations are equally likely to be generated.

Assumptions

The given array is not null

思路： 等于做所有permutation其中的一条从上到下的线
 */

public class Solution {
  public void shuffle(int[] array) {
    // Write your solution here.
    for (int i = 0 ; i < array.length; i++) {
        int randomInt = i + (int) (Math.random() * (array.length - i));
        swap (array, i ,randomInt);
    }
    
  }
  private void swap (int[] array, int i, int j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
  }
}
