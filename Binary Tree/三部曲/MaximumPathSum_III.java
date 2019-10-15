/**
Given a binary tree in which each node contains an integer number. Find the maximum possible subpath sum(both the starting and ending node of the subpath should be on the same path from root to one of the leaf nodes, and the subpath is allowed to contain only one node).

Assumptions

The root of given binary tree is not null
Examples

   -5

  /    \

2      11

     /    \

    6     14

           /

        -3

The maximum path sum is 11 + 14 = 25

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.


 */
public class Solution {
  private int globalMax = Integer.MIN_VALUE;
  public int maxPathSum(TreeNode root) {
    // Write your solution here
    if (root == null) {
      return globalMax;
    }
    helper(root);
    return globalMax;
  }
  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left);
    int right = helper(root.right);

    // 如果left,right都是负数, 则直接取0, 另立山头
    int curMax = Math.max(Math.max(left, right), 0) + root.key;
    globalMax = Math.max(globalMax, curMax);
    return curMax;
  }
}