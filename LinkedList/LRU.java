/**
经典老题, 主要是训练对链表的插入,删除, 查询操作和让链表和map关联, 
这里注意Node要存(key,value) 而map对应关系应为(key,Node) 不然在delete 的时候要做一次遍历

这里首先确认数据结构:
map, 双向链表, capacity, 记数size
construtor: 初始化map, 双向链表dummy head 做出来, 关联打出来
现在考虑实现get和put方法
get: 查map, 有就返回,没有就-1
list上要 重排序, get过的值放队列头部
put:
首先2个case, 因为可能存在更新值, 分key在 和 不在得情况
key在: map更新值, list 重排序
不在: 再细分2个case, 看size满没满 
    没满: 直接插入map, 插入list
    满了:
        找到list最后点, map里删去, list中删去
        map里插入新值, list中插入新值
完事, 现在再来写链表的 insert delete 和 reorder, 基本功, 一个字练, 注意画个图, 把链表元素怎么对应搞清楚就行
 */

class LRUCache {
  private class Node {
    int key;
    int value;
    Node next;
    Node prev;
    Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
  private Map<Integer, Node> map;
  private int capacity;
  private int size = 0;
  // initial with dummy node
  private Node head;
  private Node tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    // init map
    map = new HashMap<>();
    // init list
    head = new Node(0,0);
    tail = new Node(0,0);
    head.next = tail;
    tail.prev = head;

  }

  public int get(int key) {
    if (map.containsKey(key)) {
      reOrderList(map.get(key));
      return map.get(key).value;
    } else {
      return -1;
    }

  }

  public void put(int key, int value) {
    // 2个case 有key 和 没key
    if (map.containsKey(key)) {
      // 只更新值
      Node ori = map.get(key);
      ori.value = value;
      map.put(key,ori);
      reOrderList(ori);
    } else {
      if (size < capacity) {
        size++;
      } else {
        map.remove(tail.prev.key);
        delete(tail.prev);
      }
      Node inNode = new Node(key,value);
      map.put(key, inNode);
      insert(inNode);
    }
  }
  private void reOrderList(Node node) {
      delete(node);
      insert(node);
  }
  private void insert(Node in) {
    Node temp = head.next;
    head.next = in;
    in.prev = head;
    in.next = temp;
    temp.prev = in;
  }
  private void delete(Node cur) {
    cur.prev.next = cur.next;
    cur.next.prev = cur.prev;
  }
}
