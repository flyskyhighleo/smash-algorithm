package two.pointers;

/*
https://leetcode.com/problems/valid-palindrome-iv/description/
You are given a 0-indexed string s consisting of only lowercase English letters. In one operation, you can change any
character of s to any other character.
Return true if you can make s a palindrome after performing exactly one or two operations, or return false otherwise.
************************************************************************************************************************
two pointers. l and r
if l == r, compare l + 1, r - 1
else, replace, log replace time + 1, compare l + 1, r - 1,
when replace time >= 2 before log for the current mismatching, return false.
return true at the end

 */
public class ValidPalindrome_IV {
    public boolean makePalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        int replaced = 0;

        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                if (replaced >= 2) {
                    return false;
                }
                replaced += 1;
            }
            l += 1;
            r -= 1;
        }

        return true;
    }
}
