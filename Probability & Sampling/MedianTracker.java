/**
Given an unlimited flow of numbers, keep track of the median of all elements seen so far.

You will have to implement the following two methods for the class

read(int value) - read one value from the flow
median() - return the median at any time, return null if there is no value read so far
Examples

read(1), median is 1
read(2), median is 1.5
read(3), median is 2
read(10), median is 2.5
......
思路: 2个PQ,一个maxheap 一个minheap, 保持PQ平衡, 保持按大小顺序排好
case1. size 基数, !!peek!! maxHeap
case2. size 偶数, peak 两个pq / 2
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
  PriorityQueue<Integer> minHeap; // second half
  PriorityQueue<Integer> maxHeap; // first half

  public Solution() {
    // add new fields and complete the constructor if necessary.
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>(10, Comparator.reverseOrder());
  }
  public void read(int value) {
    // write your implementation here.
    if (maxHeap.isEmpty() || value <= maxHeap.peek()) {
      maxHeap.add(value);
    } else {
      minHeap.add(value);
    }
    // re-order
    if (maxHeap.size() - minHeap.size() >= 2) {
      minHeap.add(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.add(minHeap.poll());
    }
  }

  public Double median() {
    // write your implementation here.
    if (minHeap.isEmpty() && maxHeap.isEmpty()) {
      return null;
    } else {
      if (minHeap.size() != maxHeap.size()) {
        return Double.valueOf(maxHeap.peek());
      } else {
        return Double.valueOf(minHeap.peek() + maxHeap.peek()) / 2;
      }
    }
  }
}
