/**
Given a binary tree in which each node contains an integer number. The diameter is defined as the longest distance from one leaf node to another leaf node. The distance is the number of nodes on the path.

If there does not exist any such paths, return 0.

Examples

    5

  /    \

2      11

     /    \

    6     14

The diameter of this tree is 4 (2 → 5 → 11 → 14)

How is the binary tree represented?

思考： 这题是必须一头leaf 到 另一头也是leaf, 所以在处理max的时候，得限定条件
必须是左右孩子都有的时候才更新，不然无效
 */
public class Solution {
  int max = 0;
  public int diameter(TreeNode root) {
    // Write your solution here
    if (root == null) {
      return max;
    }
    helper(root);
    return max;
  }
  private int helper(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = helper(root.left);
    int right = helper(root.right);
    int curMax = Math.max(left, right) + 1;
    if (root.left != null && root.right != null) {
      max = Math.max(max, left + right + 1);
    }
    return curMax;
  }
}
