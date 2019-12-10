/**
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 
确保明确物理意义，left 所有数要小于 right, 这样求mid的时候才有意义
*/

class MedianFinder {
  PriorityQueue<Integer> left;
  PriorityQueue<Integer> right;
  public MedianFinder() {
    left = new PriorityQueue<>((a,b) -> (b - a));
    right = new PriorityQueue<>();
  }

  public void addNum(int num) {
    if (left.isEmpty() || left.peek() > num) {
      left.add(num);
    } else {
      right.add(num);
    }
    // re-balance
    if (left.size() > right.size() + 1 || left.size() + 1 < right.size()) {
      if (left.size() > right.size()) {
        right.add(left.poll());
      } else {
        left.add(right.poll());
      }
    }
  }

  public double findMedian() {
    if (left.size() == right.size()) {
      return ((double) left.peek() + (double) right.peek()) / 2;
    } else {
      return left.size() > right.size() ? (double) left.peek() : (double) right.peek();
    }
  }
}