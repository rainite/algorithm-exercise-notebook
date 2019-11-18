/**
实现一个trie, root不存东西
*/
// Map实现版本
class TrieNode{
  Map<Character, TrieNode> map = new HashMap<>();
  boolean isWord = false;
}
class Trie {
  TrieNode root;
  TrieNode cur;
  /** Initialize your data structure here. */
  public Trie() {
    root = new TrieNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    // base
    cur = root;
    for (int i = 0; i < word.length(); i++) {
      cur.map.putIfAbsent(word.charAt(i), new TrieNode());
      cur = cur.map.get(word.charAt(i));
    }
    cur.isWord = true;
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    cur = root;
    for (int i = 0; i < word.length(); i++) {
      if (cur.map.containsKey(word.charAt(i))) {
        cur = cur.map.get(word.charAt(i));
      } else {
        return false;
      }
    }
    return cur.isWord;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    cur = root;
    for (int i = 0; i < prefix.length(); i++) {
      if (cur.map.containsKey(prefix.charAt(i))) {
        cur = cur.map.get(prefix.charAt(i));
      } else {
        return false;
      }
    }
    return true;
  }
}

// 数组实现版本, 丧失了灵活性, 不建议
class Trie {
  private TrieNode root;

  /** Initialize your data structure here. */
  public Trie() {
    root = new TrieNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    TrieNode node = root;

    for (char letter : word.toCharArray()) {
      if (!node.containsKey(letter)) {
        node.put(letter, new TrieNode());
      }
      node = node.get(letter);
    }
    node.isEnd = true;
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    TrieNode node = root;

    for (char letter : word.toCharArray()) {
      if (!node.containsKey(letter)) {
        return false;
      }
      node = node.get(letter);
    }
    return node.isEnd;
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    TrieNode node = root;

    for (char letter : prefix.toCharArray()) {
      if (!node.containsKey(letter)) {
        return false;
      }
      node = node.get(letter);
    }

    return true;
  }

  class TrieNode {
    private TrieNode[] nodes;
    boolean isEnd;

    TrieNode() {
      nodes = new TrieNode[26];
    }

    void put(char letter, TrieNode node) {
      nodes[letter - 'a'] = node;
    }

    TrieNode get(char letter) {
      return nodes[letter - 'a'];
    }

    boolean containsKey(char letter) {
      return get(letter) != null;
    }
  }