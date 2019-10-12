/**
思路: 主要用hashset 记录 val 的index, arraylist记录val;
insert:
arraylist加尾巴
map记录index
delete
思路为吧delete的元素照出来,用array尾部的元素替换, 如果本来就是最后一个元素, 直接删
1. get index of val
2. 记录array最后一个值temp
3. set (index,temp) [交换]
4. 删除array中最后一个元素
5. 删除map中这个元素val
6. 更新map记录新的index of temp
getRandom
直接公式取就好
 */
class RandomizedSet {
  Map<Integer, Integer> map;
  List<Integer> res;
  /** Initialize your data structure here. */
  public RandomizedSet() {
    map = new HashMap<>();
    res = new ArrayList<>();

  }

  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  public boolean insert(int val) {
    if (map.containsKey(val)) {
      return false;
    }
    map.put(val, res.size());
    res.add(val);
    return true;
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false;
    }
    int index = map.get(val);
    if (index < res.size() - 1) {
      int temp = res.get(res.size() - 1);
      res.set(index, temp);
      map.put(temp, index);
    }
    map.remove(val);
    res.remove(res.size() - 1);
    return true;
  }

  /** Get a random element from the set. */
  public int getRandom() {
    int random = (int) (Math.random() * res.size());
    return res.get(random);
  }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */