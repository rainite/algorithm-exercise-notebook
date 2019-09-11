/**
思路：在逻辑（中）干事，遍历顺序为左中右，所以一路向左到底，一路存入stack；最底为左
cur为null时，说明左没了，pop，加入res，（此时cur为逻辑中，干事，加res）
cur = cur.right，再循环 （进右枝）
 */
public class Solution {
  public List<Integer> inOrder(TreeNode root) {
    // Write your solution here
    List<Integer> res = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
      if (cur != null) {
        stack.push(cur);
        cur = cur.left;
      } else {
        cur = stack.pop();
        res.add(cur.key);
        cur = cur.right;
      }
    }
    return res;

  }
}
