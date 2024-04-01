package heap;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-median-from-data-stream/description/

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value,
and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
========================================================================================================================
Naive approach: list + sort
Time of findMedian: O(nlogn)

Two heaps: maxHeap and minHeap
maxHeap contains smaller number
minHeap contains larger number
size of maxHeap - size of minHeap = 0 or 1

when adding a number, compare the number with maxHeap top
add to maxHeap if the number > maxHeap top, otherwise add to minHeap.

maintain the balance between maxHeap and min minHeap

 */
public class MedianFinder {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    public MedianFinder() {
        this.maxHeap = new PriorityQueue<>((a, b) -> b - a);
        this.minHeap = new PriorityQueue<>((a, b) -> a - b);
    }

    public void addNumber(int num) {
        int maxTop = maxHeap.isEmpty() ? Integer.MIN_VALUE : maxHeap.peek();

        if (num > maxTop) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }

        balance(maxHeap, minHeap);
    }

    private void balance(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
        if (pq1.size() == pq2.size()) {
            return;
        }

        while (pq1.size() - pq2.size() > 1) {
            pq2.offer(pq1.poll());
        }

        while (pq2.size() - pq1.size() >= 1) {
            pq1.offer(pq2.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
