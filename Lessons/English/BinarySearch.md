# Binary Search Basics
## Application Scenarios
1. Searching for a target value.
2. Finding the left boundary (the leftmost position where the value is greater than or equal to the target).
3. Finding the right boundary (the rightmost position where the value is less than or equal to the target).
## Time Complexity: O(logN)
## Framework
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
## Understanding Boundary Search
**Finding Left Boundary:**
1. Find the leftmost position where the value is greater than or equal to the target.
2. This position ensures that the array remains sorted after inserting the target value.
3. If the return value is -1, it indicates that all array elements are smaller than the target value.

**Finding Right Boundary:**
1. Find the rightmost position where the value is less than or equal to the target.
2. This position ensures that the array remains sorted after inserting the target value.
3. If the return value is -1, it indicates that all array elements are greater than the target value.

## Ordered vs. Unordered
**Binary search doesn't necessarily require a sorted array!**

For example: Finding a peak.

Thus, if it can be determined that **one side must have** or **one side must not have** something, binary search can be employed.

## Generalization of Binary Search (Binary Search for Answers)

When direct searching is not obvious, if a monotonic relationship exists between the independent variable (possible answer values) and the dependent variable (usually compared with constraint conditions), binary search can be used to determine the answer.

Determine (estimate) the range of possible answers.
Understand the monotonic relationship between the independent and dependent variables.
If a monotonic relationship exists, construct the monotonic function f(x).
By comparing constraint conditions, use binary search to narrow down the search range until the answer is found.

**Key Points**

1. Understand monotonicity.
2. Construct the monotonic function.

## Framework
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

// monotonic function
private int f(int[] nums, int x) {

}
```





