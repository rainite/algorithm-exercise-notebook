/*
错误代码示范！ 错误例子：{"5","4","8","2","6"} 注意二叉搜索树的大小是应用于整颗子树的！下面错误代码只检查了一层的大小关系
*/
// public class Solution {
//   public boolean isBST(TreeNode root) {
//     // Write your solution here
//     if (root == null) {
//       return true;
//     }
//     if (root.left != null && root.left.key >= root.key) {
//       return false;
//     }
//     if (root.right != null && root.right.key <= root.key) {
//       return false;
//     }
//     boolean left = isBST(root.left);
//     boolean right = isBST(root.right);
//     return left && right;
//   }
// }

/* 思路，记录当前root.val 值应该在什么范围，左儿子则应该在[l,root.val) 右儿子则是(root.val,r]
巧妙运用null 来避开root.val就等于minvalue or maxvalue的情况
*/
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    private boolean helper(TreeNode root, Integer left, Integer right) {
        // base
        if (root == null) {
            return true;
        }
        if (left != null && left >= root.val ||
            right != null && right <= root.val) {
            return false;
        }

        boolean lChild = helper(root.left,left, root.val);
        boolean rChild = helper(root.right, root.val, right);
        return lChild && rChild;
    }
}

