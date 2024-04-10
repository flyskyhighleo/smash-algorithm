package two.pointers;

/*
https://leetcode.com/problems/reverse-words-in-a-string-ii/description/
Given a character array s, reverse the order of the words.
A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
Your code must solve the problem in-place, i.e. without allocating extra space.
 */
public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int i = 0;
        while (i < s.length) {
            int j = i;
            while (j < s.length && s[j] != ' ') {
                j++;
            }
            reverse(s, i, j - 1);
            i = j + 1;
        }
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char t = s[start];
            s[start] = s[end];
            s[end] = t;
            start += 1;
            end -= 1;
        }
    }
}
