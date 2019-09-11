import java.util.LinkedList;
import java.util.Queue;

/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
/* 思路： 层级遍历，一旦遇到一个叶子节点为null，那么后面的儿子们都只能是null
if left or right == null, set flag
case 1. flag not set, just add
case 2. flag set, left or right has child, return false
*/
public class Solution {
    public boolean isCompleted(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        boolean flag = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            if (cur.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.add(cur.left);
            }
            // same for the right
            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.add(cur.right);
            }
        }
        return true;
    }
}
