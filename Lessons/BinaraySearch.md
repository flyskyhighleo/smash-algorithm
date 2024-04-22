# 二分查找基础

## 应用场景

1. 查找目标值
2. 查找左边界  ( >= 目标值的最左边）
3. 查找右边界 ( <= 目标值的最右边）

## 时间复杂度：O(logN)

## 框架

```java
// search target
public int search(int[] nums, int target) {
	int l = 0;
	int r = nums.length - 1;
	while (l <= r) {
		int m = l + (r - l) / 2;
		if (nums[m] == target) {
			return m;
		} else if (nums[m] < target) {
			l = m + 1;
		}  else {
			r = m - 1;
		}
	}
	return -1;
}

// search left boundary.
// search the index where index the most left position such that nums[index] >= target
public int search(int[] nums, int target) {
	int l = 0;
	int r = nums.length - 1;
	int res = -1;
	while (l <= r) {
		int m = l + (r - l) / 2;
		if (nums[m] >= target) {
			res = m;
			r = m - 1;
		} else {
			l = m + 1;
		} 
	}

	return res;
}

// search right boundary
// search the index where index is the right most position such that nums[index] <= target
public int search(int[] nums, int target) {
	int l = 0;
	int r = nums.length - 1;
	int res = -1;
	while (l <= r) {
		int m = l + (r - l) / 2;
		if (nums[m] <= target) {
			ares = m;
			l = m + 1;
		} else {
			r = m - 1;
		}
	}
	return res;
}
```

## 查找边界值的理解

查找左边界：

1. 找到 >= 目标值的最左边位置。
2. 该位置是插入目标值后，能保证数组仍然有序。
3. 如果返回值是 -1，说明数组元素都小于目标值。

查找右边界：

1. 找到 <= 目标值的最右边位置。
2. 该位置是插入目标值后，仍能保证数组有序。
3. 如果返回值是 -1，说明数组元素都大于目标值。

## 有序 vs 无序

**二分查找不一定非要发生在有序数组上！！！**

例如：寻找峰值

所以，如果可以确定“某侧必有” 或者 “某侧必没有”，就可以使用二分查找。

# 二分查找的泛化 （二分答案法）

当直接查找不明显时，如果发现自变量（答案可能取值）与因变量（通常是与约束条件相比较的值）之间存在单调关系，那么就可以使用二分查找来确定答案。

1. 确定（估计）答案的取值范围
2. 明确自变量与因变量的单调关系
3. 如果单调关系存在，构建单调函数 f(x)
4. 通过比较约束条件，使用二分查找缩小搜索范围，直到找到答案

## 关键

1. 明确单调性
2. 构建单调函数

## 框架

```java
public int search(int[] nums, int cons) {
	// 'estimate' answer scope. e.g. [0, max in input]
	int left = 0;
	int right = max of nums;
	int res = -1;
	
	// determine if there's monotonic relation
	// as answer increase, how does its response change
	// define the monitonic function

	// do binary search agains answer scope
	// search the target?
	// search left most such that >= constrain
	// search right most such that <= constrain
	while (left <= right) {
		int mid = left + (right - left) / 2;
		int candidate = f(nums, mid);
		if (candidate == cons) {
			// shrink left?
			// shrink right?
			// update res
		} else if (candidate < cons) {
			// shrink searching range
		} else {
			// shrink searching range
		}
	}

	return res;

}

private int f(int[] nums, int x) {

}
```