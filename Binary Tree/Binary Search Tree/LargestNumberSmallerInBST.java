/**
In a binary search tree, find the node containing the largest number smaller than the given target number.

If there is no such number, return -2^31.

Assumptions:

The given root is not null.
There are no duplicate keys in the binary search tree.
Examples

    5

  /    \

2      11

     /    \

    6     14

largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)

largest number smaller than 10 is 6

largest number smaller than 6 is 5

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4

思路: 要比target小, 那就让遍历的时候发现mid.key <= target时往右走,并更新res就好了
本质仍然是搜索
 */
public class Solution {
  public int largestSmaller(TreeNode root, int target) {
    // Write your solution here
    TreeNode res = null;
    TreeNode mid = root;
    while (mid != null) {

      if (mid.key >= target) {
        mid = mid.left;
      // 往右走才说明有答案出现
      } else {
        res = mid;
        mid = mid.right;
      }
    }
    return res == null ? Integer.MIN_VALUE : res.key;
  }
}
