package two.pointers;

/*
https://leetcode.com/problems/valid-palindrome-ii/description/
Given a string s, return true if the s can be palindrome after deleting at most one character from it.
***********************************************************************************************************************
two pointers, left and right pointers.
If left and right are same letters, continue moving left to right, right to left and compare
if there's mismatch, skip the mismatch, and mark that we have seen difference
if there's another mismatch, return false directly

 */
public class ValidPalindrome_II {
    public boolean validatePalindrome(String s) {
        return validate(s, 0, s.length() - 1, 0);
    }

    public boolean validate(String s, int left, int right, int diff) {
        if (diff > 1) {
            return false;
        }

        if (left >= right) {
            return true;
        }

        if (s.charAt(left) == s.charAt(right)) {
            return validate(s, left + 1, right - 1, diff);
        } else {
            return validate(s, left + 1, right, diff + 1) || validate(s, left, right - 1, diff + 1);
        }
    }
}
