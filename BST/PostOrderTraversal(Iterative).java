/**
比较复杂，暂时还没有发现一个高纬度的思路，只能背细节
 */
public class Solution {
  public List<Integer> postOrder(TreeNode root) {
    // Write your solution here
    List<Integer> res = new ArrayList<Integer>();
    if (root == null) {
      return res;
    }
    Deque<TreeNode> stack = new LinkedList<TreeNode>();
    stack.push(root);
    TreeNode prev = null;
    while (!stack.isEmpty()) {
      TreeNode cur = stack.peek();
      // 往下遍历时
      if (prev == null || cur == prev.left || cur == prev.right) {
        // go left first
        if (cur.left != null) {
          stack.push(cur.left);
        } else if (cur.right != null) {
          stack.push(cur.right);
        } else {
          // 左右都是空，访问完了，现在位置是逻辑"中"，干事
          stack.pop();
          res.add(cur.key);
        }
      } else if (prev == cur.left) {
        // 从左往上走时，还有向右边遍历
        if (cur.right != null) {
            stack.push(cur.right);
        } else {
            // 此时右没有，遍历完成，现在是逻辑"中"
            stack.pop();
            res.add(cur.key);
        }
      } else {
        // 从右边往上走时，此时右遍历完成，是逻辑"中"
        stack.pop();
        res.add(cur.key);
      }
      prev = cur;
    }
    return res;
  }
}
