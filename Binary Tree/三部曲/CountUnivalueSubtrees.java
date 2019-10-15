/**
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5
return 4.

思路: 这里答案取决于两个变量
1. 子树儿子是否与自己相等, boolean
2. 总体计数, 如果所有儿子与root相等, count += 1

三部曲:
1. 问左右孩子是否出现值不相等的情况
2. 如果出现不相等(false) 直接返回false, else 判断值, 相等则count++, 否则返回false
 2.1 这里4个case, 分别为左右儿子有没有 * 儿子相等不, 可以利用短路技巧来写if
3. 向parent返回是否root.key相等
 */
public class Solution {
    int count;
    
    public int countUnivalSubtrees(TreeNode root) {
        count = 0;
        helper(root);
        return count;
    }
    
    boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean left = helper(root.left);
        boolean right = helper(root.right);
        
        // 利用'短路'来快速判断null的情况
        if (left && right && 
           (root.left == null || root.val == root.left.val) && 
           (root.right == null || root.val == root.right.val)) {
            count++;
            return true;
        }
        
        return false;
    }
}