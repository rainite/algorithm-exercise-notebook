/**
Invert a binary tree.

     4
   /   \
  2     7
 / \   / \
1   3 6   9
to
     4
   /   \
  7     2
 / \   / \
9   6 3   1

三部曲：
1. 向左右儿子要什么？ 要翻转好的子树
2. 当前层干什么？ 翻转！
3. 给parent什么？ 给自己
 */
public class Solution {
  public TreeNode invertTree(TreeNode root) {
      if (root == null) {
          return null;
      }
      TreeNode right = invertTree(root.right);
      TreeNode left = invertTree(root.left);
      root.left = right;
      root.right = left;
      return root;
  }
}
