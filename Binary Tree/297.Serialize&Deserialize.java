/**
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

DFS方式去编码最简单， 注意编码解码的时候都只是传的指针，所以代码看起来有点怪
 */
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    code(root, sb);
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] dataArray = data.split(",");
    List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
    return decode(dataList);
  }
  
  void code(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append("null,");
      return;
    }
    sb.append(root.val + ",");
    code(root.left, sb);
    code(root.right, sb);
  }
  
  TreeNode decode(List<String> list) {
    if (list.get(0).equals("null")) {
      list.remove(0);
      return null;
    }
    
    TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
    list.remove(0);
    root.left = decode(list);
    root.right = decode(list);
    return root;
  }

}