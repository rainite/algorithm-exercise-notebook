/**
Given a binary search tree and a target number, find the in-order successor of the given number in the tree, and return its value. If there is no such node, then return -1.

Assumptions:

1. There are no duplicate values in the BST.

2. Value of all nodes in the BST are positive integer.

3. The given value must be in the BST. 

思考: 就是找比p小的最大的数, 完全类似BS的题, 吧root当成'mid', 其他都一样
 */
public class Solution {
  public int inOrderSuccessor(TreeNode root, int p) {
    // Write your solution here
    TreeNode res = null;
    TreeNode mid = root;
    while (mid != null) {
      if (mid.key > p) {
        res = mid;
        mid = mid.left;
      } else {
        mid = mid.right;
      }
    }
    return res == null ? -1 : res.key;
  }
}