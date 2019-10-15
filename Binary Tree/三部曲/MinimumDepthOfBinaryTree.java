/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }

Given a binary tree, find its minimum depth. The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Example:
Given the below binary tree

             5

          /       \

        3         8

           \

               4

minimum depth is 2,path is 5→8.

思路： 一定要是从根到一个叶子，所以三部曲
1. 向儿子们要一条直上直下的高度信息
2. 处理如何向parent返回值， 3个case
    1. left == right == 0， 直接返回 1
    2. left，right 有一边是0， 定义位必须直上直下， 所以返回不是0的那一边
    3. left，right 都不是0， 返回 最小的那一边
3. 把处理好的2返回给parent

 */
public class Solution {
  public int minDepth(TreeNode root) {
    // Write your solution here
    if (root == null) {
      return 0;
    }
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left == 0 || right == 0) {
      // case1. 都是0， 返回1
      // case2. 有一边是0，返回不是0的那一边
      return Math.max(left, right) + 1;
    }
    // case3. 都不是0，返回小的那一边
    return Math.min(left, right) + 1;
  }
}


