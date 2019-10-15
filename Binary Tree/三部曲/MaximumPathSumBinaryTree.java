/**
Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).

Examples

  -15

  /    \

2      11

     /    \

    6     14

The maximum path sum is 6 + 11 + 14 = 31.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4

思路： 标准树的题，分治法，想清楚
step1. 问左右孩子要什么
step2. 拿到东西了后，当前层做些什么
step3. 向parent 返回什么， 注意和step1 物理意义连续！

这里，向左右孩子要包括自己root的一条直上直下路径和
当前层处理globalMax，if valid，题目要求必须是要到叶子节点，所以只能是根左右都有才是合法情况
返回：case1. 根左右都有，选最大的一条路
case2. 少左或少右， 返回另一边， 切记不能Math.Max，因为我们null节点返回的是0
case3. 都没有， 返回root

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
    
    if (root.left != null && root.right != null) {
      globalMax = Math.max(globalMax, left + right + root.key);
      return Math.max(left + root.key, right + root.key);
    }
    return root.left == null ? root.key + right : root.key + left;
  }
}
