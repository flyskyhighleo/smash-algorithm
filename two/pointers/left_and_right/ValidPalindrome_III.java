package two.pointers.left_and_right;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/valid-palindrome-iii/description/
Given a string s and an integer k, return true if s is a k-palindrome.
A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
Example:
s = "abcdeca", k = 2. return true

************************************************************************************************************************

ValidPalindrome_II is actually k = 1.
The solution for this problem is still two pointers (left and right).
We will also use Dynamic Programming. We can either use top-down with memory or bottom-up.

Top-down DP
Define a function that returns true if s is valid palindrome after deleting at most k letters, otherwise false.
    boolean dp(String s, int i, int j, int k)

To check how many times we removed letters, we still need a variable to track the times of deletion
Updated function
    boolean dp(String s, int i, int j, int k, int removed)

Goal is to get result of dp(s, 0, len - 1, k, removed)
Let's focus on specific letters at index i and j
1. if s[i] == s[j], continue next positions, i + 1, j - 1
2. else, delete and check next position
     dp(s, i + 1, j, k, removed + 1) or dp(s, i, j - 1, k removed + 1)

base case:
    if removed > k, return false
    if (i >= j) return true

to avoid duplicates sub-problems, let's use memory

Solution: see ValidPalindromeIII_top_down_I
========================================================================================================================

Top-down DP 2
Another way to think about DP solution.
Let's define the function to return minimum numbers of removal to make s a valid palindrome
    int dp(s, i, j)
At the end, we need to compare the result of min removal with k.
    if min removal <= k, return true
    else, return false

Solution: see ValidPalindrome_III_top_down_II
========================================================================================================================

Bottom-up DP
Solution: see ValidPalindrome_III_bottom_up

 */
public class ValidPalindrome_III {

}