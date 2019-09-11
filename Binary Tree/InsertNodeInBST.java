/*
Insert a node in BST
人生三问：
	1. 问左右孩子要什么
	2. 在当前层干什么
	3. 给父节点返回什么
*/

public TreeNode insert(TreeNode root, int key) {
	//退出，函数目的为找到空就插入(新建node)
	if (root == null) {
		TreeNode newNode = new TreeNode(key);
		return newNode;
	}

	if (root.key < key) {
		//node小于key就往右遍历，这里赋值只有在最后一层右儿子是null的时候有实际意义
		root.right = insert(root.right,key);
	} else if (root.key > key) {
		root.left = insert(root.left, key);
	}
	
	return root;
}

//Iterasive

public TreeNode insert(TreeNode root, int key) {
	TreeNode newNode = new TreeNode(key);
	// corner
	if (root == null) {
		return newNode
	}
	TreeNode cur = root;
	// search
	while (cur.key != target) {
		// 2个大条件，大于左走小于右走
		if(cur.key > target) {
			if (cur.left != null) {
				cur = cur.left;
			} else {
				cur.left = newNode;
				break;
			}
		} else {
			if (cur.right != null) {
				cur = cur.right;
			} else {
				cur.right = newNode;
				break;
			}
		}

	}
	return root;
}