/**
Given a binary Tree and the two keys, determine whether the two nodes are cousins of each other or not. Two nodes are cousins of each other if they are at the same level and have different parents.

Assumptions:

It is not guranteed the two keys are all in the binary tree.
There are no duplicate keys in the binary tree.
Examples:

     6
   /   \
  3     5
 / \   / \

7   8 1   13
7 and 1, result is true.
3 and 5, result is false.
7 and 5, result is false.
 */

// 当前层如果有, 则一定要在当前层把另一个node加进来
// 并且不能是同一个parent node
public class Solution {
  public boolean isCousin(TreeNode root, int a, int b) {
    // Write your solution here
    if (root == null || root.key == a || root.key == b) {
      return false;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      boolean flagA = false;
      boolean flagB = false;
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        boolean curLevelFlag = false;
        if (cur.left != null) {
          if (cur.left.key == a || cur.left.key == b) {
            if (cur.left.key == a) {
              flagA = true;
            } else {
              flagB = true;
            }
            curLevelFlag = true;
          }
          queue.offer(cur.left);
        }
        if (cur.right != null) {
          if ((cur.right.key == a || cur.right.key == b) && !curLevelFlag) {
            if (cur.right.key == a) {
              flagA = true;
            } else {
              flagB = true;
            }
          }
          queue.offer(cur.right);
        }
      }
      if (flagA && flagB) {
        return true;
      }
    }
    return false;
  }
}
