/**
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
注意这题在当前层做判断是比较好的选择， base就简单的触底退出
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, res, new ArrayList<>(), 0, sum);
        return res;
    }
    private void helper(TreeNode root, List<List<Integer>> res,
     List<Integer> aug, int curSum, int sum) {
        // base
        if (root == null) {
            return;
        }

        aug.add(root.val);

        if (root.left == null && root.right == null &&
        curSum + root.val == sum) {
            res.add(new ArrayList<>(aug));
        }
        
        helper(root.left, res, aug, curSum + root.val, sum);
        helper(root.right, res, aug, curSum + root.val, sum);
        aug.remove(aug.size() - 1);
    }
}