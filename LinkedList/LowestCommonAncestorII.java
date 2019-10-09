/**
Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

Assumptions

There is parent pointer for the nodes in the binary tree

The given two nodes are not guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */

public class Solution {
  public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
    // Write your solution here.
    int left = getHeight(one);
    int right = getHeight(two);

    if (left > right) {
      for (int i = 0; i < left - right; i++) {
        one = one.parent;
      }
    } else if (right > left) {
      for (int i = 0; i < right - left; i++) {
        two = two.parent;
      }
    }
    while (one != two) {
      one = one.parent;
      two = two.parent;
    }
    return one;
  }
  private int getHeight(TreeNodeP root) {
    int count = 0;
    while (root != null) {
      root = root.parent;
      count++;
    }
    return count;
  }
}