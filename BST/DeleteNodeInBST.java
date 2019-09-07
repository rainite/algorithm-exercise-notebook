/*
case 1. node to be deleted has no children, just remove
case 2. node has left or right child, just let root = root.left or root.right
case 3. node has both left and right children, pick smallest of right subtree or largest from left subtree
	take right way for example
	case 3.1 node.right has no left child, just move node.right up
	case 3.2 node.right has left child, find smallest node and move it up.
*/

public TreeNode delete(TreeNode root, int target) {
	if (root == null) {
		return null;
	}

	// find target node
	if (root.val > target) {
		root.left = delete(root.left, target);
		return root;
	} else if (root.val < target) {
		root.right = delete(root.right, target);
		return root;
	}

	// now root != null && root.val == target
	// case 1,2
	if (root.left == null) {
		return root.right;
	} else if (root.right == null) {
		return root.left;
	}

	// now root.left != null && root.right != null
	//case 3.1
	if (root.right.left == null) {
		root.right.left = root.left;
		return root.right;
	}
	//case 3.2
	//find smallest node in subtree
	TreeNode smallest = findSmallest(root.right);
	// connect the smallest with root.left and root.right
	smallest.left = root.left;
	smallest.right = root.right;
	//return smallest;
	return smallest;
}

private TreeNode findSmallest(TreeNode cur) {
	TreeNode prev = cur;
	cur = cur.left;
	while (cur.left != null) {
		prev = cur;
		cur = cur.left;
	}
	// cur is the smallest, prev is cur's parent
	// !! don't drop possible right children !!
	prev.left = prev.left.right;
	return cur;
}

