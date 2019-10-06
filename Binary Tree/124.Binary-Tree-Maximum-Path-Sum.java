/**
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

思路: 标准分治法,
向左右儿子要到现在为止的最大路径值
维护全局max, 每次max有3中情况
1. 只要root
2. 要root和左儿子 或者 root和右儿子
3. root作为'倒拐点' 左中右都要
 */
class Solution {
  int max = Integer.MIN_VALUE;
  public int maxPathSum(TreeNode root) {
    helper(root);
    return max;
  }
  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left);
    int right = helper(root.right);
    int curMax = 0;
    if (root.val > left + root.val && root.val > right + root.val) {
      curMax = root.val;
    } else {
      curMax = Math.max(left + root.val, right + root.val);
    }
    max = Math.max(max, Math.max(curMax, root.val + left + right));
    return curMax;
  }
}