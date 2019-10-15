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

// 思路，记录当前root.val 值应该在什么范围，左儿子则应该在[l,root.val) 右儿子则是(root.val,r]
public class Solution {
    public boolean isBST(TreeNode root) {
        // Write your solution here
        return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode root,int l, int r) {
        if (root == null) {
            return true;
        }
        if (root.key <= l || root.key >= r) {
            return false;
        }

        return isBST(root.left,l,root.key) && isBST(root.right,root.key,r);
    }
}

