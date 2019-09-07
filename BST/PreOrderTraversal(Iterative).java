public class Solution {
  public List<Integer> preOrder(TreeNode root) {
    // Write your solution here
    if (root == null) {
      // ArrayDeque 不能add null， 但是LinkedList可以，
      // ArrayList也可以
      return new ArrayList<Integer>();
    }
    List<Integer> res = new ArrayList<>();
    Deque<TreeNode> dp = new ArrayDeque<>();
    dp.push(root);
    while (!dp.isEmpty()) {
      TreeNode cur = dp.pop();
      res.add(cur.key);
      if (cur.right != null) {
        dp.push(cur.right);
      }
      if (cur.left != null) {
        dp.push(cur.left);
      }
    }
    return res;
  }
}
