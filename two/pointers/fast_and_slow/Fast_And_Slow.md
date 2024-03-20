# 概述
**快慢指针**是**双指针算法**的其中一种，使用一个慢指针 *slow*，和一个快指针 *fast*，在数组或者链表中游走，从而找到相关的元素。

通常来说，快指针 fast 会比慢指针 slow 走得更快，比如在单链表中寻找中间元素，快指针 fast 的速度是慢指针 slow 的两倍。
```
slow = slow.next;
fast = fast.next.next;
```
再比如，在数组中，慢指针“停在原地”，快指针向前游走去寻找元素，当满足某些条件时候，慢指针再“跟上”快指针。例如，再滑动窗口 (sliding window) 算法中，移动快指针 fast 去将新元素纳入窗口，当满足某些条件时再移动慢指针 slow 来收缩窗口。
```
while (fast < nums.length) {
	// 移动快指针，新元素入窗口
	window.add(nums[fast]);
	fast++;
	...
	// 满足条件，移动慢指针，收缩窗口
	if (满足条件）{
		window.remove(nums[left]);
		left++;
		...
	}
	...
}
```
快慢指针并不是只有 
```
fast = fast.next.next
slow = slow.next
```
这一种形式。应用场景非常多，用法灵活， 需要根据题目，来具体考虑用哪种模式，切忌死记硬背。
# 例题 
https://leetcode.com/tag/two-pointers/
1. Middle of the Linked List
2. …