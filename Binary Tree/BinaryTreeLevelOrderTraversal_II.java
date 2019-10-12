/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 思路:
 不要想用什么flag去记录, 因为是最后一层,那既然按照层级遍历, 肯定是可以把最后一层独立出来的, 
 所以我们每一层都加list, 进入新的一层把list new 一下就好了
 */
public class Solution {
  public List<Integer> levelOrderBottom(TreeNode root) {
    // Write your solution here
    if (root == null) {
      return new ArrayList<Integer>();
    }
    List<Integer> res = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      res = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        res.add(cur.key);
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
