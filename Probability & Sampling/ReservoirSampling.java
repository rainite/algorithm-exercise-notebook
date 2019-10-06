public class Solution {
  private final int k;
  int count = 0;
  List<Integer> samples = new ArrayList<>();
  
  public Solution(int k) {
    // Complete the constructor if necessary.
    this.k = k;
  }
  
  public void read(int value) {
    // 此时读进来一个数
    count++;
    // 如果list小于k, 直接加
    if (count <= k) {
      samples.add(value);
    } else {
      // 注意random生成范围是[0,count), 拍扁成int后是[0,count - 1], 正好对应arraylist的index从[0,k - 1]
      int num = (int)(Math.random() * count);
      if (num < k) {
        samples.set(num, value);
      } 
    }
  }
  
  public List<Integer> sample() {
    // Write your implementation here.
    return samples;
  }
}
