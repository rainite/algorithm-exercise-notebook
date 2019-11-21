/**
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.

思路：本质仍然是求树高，只要树高大于1，则记一个false
 */
class Solution {
  public boolean isBalanced(TreeNode root) {
    boolean[] res = new boolean[]{true};
    helper(root, res);
    return res[0];
  }
  private int helper(TreeNode root, boolean[] res) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left, res);
    int right = helper(root.right, res);
    if (Math.abs(left - right) > 1) {
      res[0] = false;
      return -1;
    }
    return Math.max(left, right) + 1;
    
  }
}