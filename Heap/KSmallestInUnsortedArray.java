// 维护一个大小为k的最大堆最符合工业设计(online, n非常大)所以这里使用最大堆
public class Solution {
    public int[] kSmallest(int[] array, int k) {
        // Write your solution here
        if (k == 0) {
          return new int[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int i : array) {
            if (pq.size() < k) {
                pq.offer(i);
            } else {
                if (i < pq.peek()) {
                    pq.poll();
                    pq.offer(i);
                }
            }
        }
        int[] res = new int[k];
        // 注意是最大堆，反序！
        for (int j = k - 1; j >= 0; j--) {
            res[j] = pq.poll();
        }
        return res;
    }
}