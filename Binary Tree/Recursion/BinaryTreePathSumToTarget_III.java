/**
Given a binary tree in which each node contains an integer number. Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), the sum of the numbers on the path is the given target number.

Examples

    5

  /    \

2      11

     /    \

    6     14

  /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4

思路: 利用2sum思想, 维护一个从root加到叶子的和的prefixSum set. 只要有prefixSum - target 也在set里, 就说明这条路径一定有一段的和等于target
用recursion 思路来做
注意雷点1: set里要放一个0, 可能加入的第一个数就是target
雷点2. 加set的时候, 是可能加不进去的, 所以不要删多了!

 */
public class Solution {
  private boolean res = false;
  public boolean exist(TreeNode root, int target) {
    // Write your solution here
    if (root == null) {
      return false;
    }
    Set<Integer> set = new HashSet<Integer>();
    // corner case if first number == target
    set.add(0);

    helper(root, target, 0, set);
    return res;
  }
  private void helper(TreeNode root, int target, int sum, Set<Integer> set) {
    if (root == null) {
      return;
    }

    sum += root.key;
    if (set.contains(sum - target)) {
      res = true;
      return;
    }
    // 注意这里可能加不进去!! 不要删多了
    boolean needRemove = set.add(sum);
    helper(root.left, target, sum, set);
    helper(root.right, target, sum, set);
    if (needRemove) {
      set.remove(sum);
    }
  }
}