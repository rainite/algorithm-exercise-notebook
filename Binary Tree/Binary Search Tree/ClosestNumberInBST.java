/**
In a binary search tree, find the node containing the closest number to the given target number.

Assumptions:

The given root is not null.
There are no duplicate keys in the binary search tree.
Examples:

    5

  /    \

2      11

     /    \

    6     14

closest number to 4 is 5

closest number to 10 is 11

closest number to 6 is 6

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4

思路: 记录一个globalMin作为trigger, 在遍历途中出现最小值就更新res node
 */


 public class Solution {
  public int closest(TreeNode root, int target) {
    // Write your solution here
    TreeNode mid = root;
    int globalMin = Integer.MAX_VALUE;
    TreeNode res = root;
    while (mid != null) {
      if (mid.key == target) {
        return mid.key;
      }
      if (Math.abs(target - mid.key) < globalMin) {
        res = mid;
        globalMin = Math.abs(target - mid.key);
      }
      
      if (mid.key > target) {
        mid = root.left;
      } else {
        mid = root.right;
      }          
    }
    return res.key;
  }
}