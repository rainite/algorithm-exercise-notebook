/**
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7

注意分情况讨论, 比insert要复杂很多, 因为insert就往null的地方加, 这题和在树里找最短的深度类似, 都比原题考虑的要复杂
 */

class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return root;
    }
    if (root.val == key) {
      //讨论4种情况，处理与被删节点左右儿子有没有有关
      if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      // 左右儿子都有，那么接左子树最大的或者右子树最小的
      } else {
        // 如果右儿子直接就是最小的
        if (root.right.left == null) {
          // 把当前的左儿子接过去
          root.right.left = root.left;
          return root.right;
        }
        TreeNode newNode = findMinInRight(root.right);
        newNode.left = root.left;
        newNode.right = root.right;
        return newNode;
      }
    }
    if (root.val > key) {
      root.left = deleteNode(root.left, key);
    } else {
      root.right = deleteNode(root.right, key);
    }
    return root;
  }
  private TreeNode findMinInRight(TreeNode root) {
    while (root.left.left != null) {
      root = root.left;
    }
    TreeNode smallest = root.left;
    //注意这里又是一个删除节点的操作！！把可能会有的右儿子接上去！
    root.left = root.left.right;
    return smallest;
  }
}