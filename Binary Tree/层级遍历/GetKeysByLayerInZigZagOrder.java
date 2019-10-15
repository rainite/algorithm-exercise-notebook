/**
Get the list of keys in a given binary tree layer by layer in zig-zag order.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [5, 3, 8, 11, 4, 1]

Corner Cases

What if the binary tree is null? Return an empty list in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

思路: 直接背一下,
要点就是, 哪边拿, 就从什么方向去读, 然后往另一个方向存
比如右边拿, 则从node的右边开始添加, 然后放在左边
 */

public class Solution {
  public List<Integer> zigZag(TreeNode root) {
    // Write your solution here
    Deque<TreeNode> deque = new LinkedList<>();
    List<Integer> res = new ArrayList<>();
    boolean flag = true;
    if (root == null) {
      return res;
    }
    deque.offer(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      for (int i = 0; i < size; i++){
        if (flag) {
          TreeNode cur = deque.pollLast();
          res.add(cur.key);
          if (cur.right != null) {
            deque.offerFirst(cur.right);
          }
          if (cur.left != null) {
            deque.offerFirst(cur.left);
          }
        } else {
          TreeNode cur = deque.pollFirst();
          res.add(cur.key);
          if (cur.left != null) {
            deque.offerLast(cur.left);
          }
          if (cur.right != null) {
            deque.offerLast(cur.right);
          }
        }
      }
      flag = !flag;
    }
    return res;
  }
}
