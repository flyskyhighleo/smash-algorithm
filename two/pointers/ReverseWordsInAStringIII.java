package two.pointers;

/*
https://leetcode.com/problems/reverse-words-in-a-string-iii/description/
Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and
initial word order.
 */
public class ReverseWordsInAStringIII {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int i = 0;
        while (i < arr.length) {
            int j = i;
            while (j < arr.length && arr[j] != ' ') {
                j++;
            }
            reverse(arr, i, j - 1);
            i = j + 1;
        }
        return new String(arr);
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
