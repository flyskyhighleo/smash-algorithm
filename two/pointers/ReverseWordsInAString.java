package two.pointers;

/*
https://leetcode.com/problems/reverse-words-in-a-string/description/

Given an input string s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
Return a string of the words in reverse order concatenated by a single space.
Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only
have a single space separating the words. Do not include any extra spaces.
========================================================================================================================
Example:
s = " The sky  is   beautiful  "
round 1: reverse each word: "ehT yks si lufituaeb"
round 2: reverse the string: "beautiful is sky The"

can also operate round 2 then round 1.
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        StringBuilder sb = trim(s);

        reverse(sb, 0, sb.length() - 1);

        int i = 0;
        while (i < sb.length()) {
            int j = i;
            while (j < sb.length() && sb.charAt(j) != ' ') {
                j++;
            }
            reverse(sb, i, j - 1);
            i = j + 1;
        }

        return sb.toString();
    }

    private StringBuilder trim(String s) {
        StringBuilder sb = new StringBuilder();
        int l = 0;
        int r = s.length() - 1;
        while (l <= r && s.charAt(l) == ' ') {
            l++;
        }
        while (l <= r && s.charAt(r) == ' ') {
            r--;
        }

        while (l <= r) {
            char c = s.charAt(l);
            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(' ');
            }
            l++;
        }

        return sb;
    }

    private void reverse(StringBuilder sb, int start, int end) {
        while (start < end) {
            char l = sb.charAt(start);
            char r = sb.charAt(end);
            sb.setCharAt(start, r);
            sb.setCharAt(end, l);
            start += 1;
            end -= 1;
        }
    }
}
