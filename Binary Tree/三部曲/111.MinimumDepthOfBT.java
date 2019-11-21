/**
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

思考：与求最大depth不一样的是，这里必须是个leaf！如果一个节点只有一个儿子，那不能说这个节点最低深度就是min(0,另一边深度)
leaf定义为没有儿子的node
 */

class Solution {
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    // both == 0
    if (left == right) {
      return left + 1;
    }
    if (left == 0 || right == 0) {
      return left == 0 ? right + 1 : left + 1;
    }
    return Math.min(left, right) + 1;
  }
}