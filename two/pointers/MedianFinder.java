package two.pointers;

import java.util.PriorityQueue;
import java.util.TreeSet;

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
TreeSet: save objects with order
Two pointers to keep track the median. left and right
number count is odd, left and right point to the same number, the median
number count is even, left and right point to max median and min median

 */
public class MedianFinder {
}
