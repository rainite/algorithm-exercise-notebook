/**
Given two nodes in a binary tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the binary tree

The given two nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

思路: 初级版本,节点一定在树里,base 包含检测就好
 */
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    // Write your solution here.
    if (root == null) {
      return null;
    }
    // the 2 nodes are guaranteed in this tree
    if (root == one || root == two) {
      return root;
    }
    
    TreeNode left = lowestCommonAncestor(root.left, one, two);
    TreeNode right = lowestCommonAncestor(root.right, one, two);

    if (left != null && right != null) {
      return root;
    }

    return left == null ? right : left;

  }
}