/**
Given a Binary Tree, return the right view of it. Right view of a Binary Tree is list of nodes visible when tree is visited from Right side, the order of the nodes in the list should be from top to bottom level of the original tree.

Examples:
           1
        /    \
       2      3
      / \    /  \
     4   5   6  7
    /            \
    9             8

  /  \

 10  11

the right view =  [1, 3, 7, 8, 11]

思路: 层级遍历,取最右边的记下来就好

 */
public class Solution {
  public List<Integer> rightView(TreeNode root) {
    // Write your solution here
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        if (i == size - 1) {
          res.add(cur.key);
        }
        if (cur.left != null) {
          queue.offer(cur.left);
        }
        if (cur.right != null) {
          queue.offer(cur.right);
        }
      }
    }
    return res;
  }
}