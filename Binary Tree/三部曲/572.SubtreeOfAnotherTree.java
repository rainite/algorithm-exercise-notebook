/**
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

思路: 判断是不是同一颗树的升级版
主递归里定义为: 在当前层能不能找到一颗和t相同的子树
三部曲,向左右儿子要: 有没有发现subtree
当前层: 看自己是不是subtree
给parent返回: 有没有发现subtree
 */
class Solution {
  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null) {
      return false;
    }
    boolean find = false;
    if (s.val == t.val) {
      find = isSame(s, t);
    }
    return find || isSubtree(s.left, t) || isSubtree(s.right, t);
  }

  private boolean isSame(TreeNode s, TreeNode t) {
    if (s == null && t == null) {
      return true;
    }
    if (s == null || t == null) {
      return false;
    }
    if (s.val != t.val) {
      return false;
    }
    return isSame(s.left,t.left) && isSame(s.right, t.right);
  }
}