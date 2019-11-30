/**
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false

注意, iterator的用法是:
new_iterator = BSTIterator(root); // 初始化
while (new_iterator.hasNext()) // 先执行hasNext()
    process(new_iterator.next());  //再执行next()

树的iterator永远可以在初始化时遍历完存list, 这样next和hasnext时间都是1, 空间n
也可以在next过程中遍历, 这样next的复杂度可能有n, 空间o(height). 整体比上面的优
 */

class BSTIterator {
  Deque<TreeNode> stack; 
  public BSTIterator(TreeNode root) {
    stack = new LinkedList<>();
    // add items
    leftMostInorder(root);
  }

  public int next() {
    TreeNode res = stack.pop();
    if (res.right != null) {
      leftMostInorder(res.right);
    }
    return res.val; 
  }

  public boolean hasNext() {
    return stack.size() > 0;
  }
  
  private void leftMostInorder(TreeNode root) {
    while (root != null) {
      stack.push(root);
      root = root.left;
    }
  }
}