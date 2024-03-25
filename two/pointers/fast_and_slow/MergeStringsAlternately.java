package two.pointers.fast_and_slow;

/*
https://leetcode.com/problems/merge-strings-alternately/
You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
If a string is longer than the other, append the additional letters onto the end of the mergedd string.
Return the merged string.
e.g.
word1 = "abcd"
word2 = "ef"
merged = "aebfcd"
************************************************************************************************************************
Two pointers, i and j
while i < word1.length or j < word2.length
    append letters at index i, then append index at index j, if index in not out of range.
************************************************************************************************************************
Notice that i and j are always at the same index
The above approach can be simplified using one pointer, i
while i is in range of max length of w1 and w2
    if i in w1 range, append letter of w1 at index i
    if i in w2 range, append letter of w2 at index i
************************************************************************************************************************
Follow up:
merge every m letters in word1, merge every n letters in word2
e.g
word1 = "abcde", m = 2
word2 = "ghijklmnop", m = 3
merged = "ab ghi cd jkl e mno p"
 */
public class MergeStringsAlternately {
    public String merge(String w1, String w2) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();

        while (i < w1.length() || j < w2.length()) {
            if (i < w1.length()) {
                sb.append(w1.charAt(i));
                i++;
            }
            if (j < w2.length()) {
                sb.append(w2.charAt(j));
                j++;
            }
        }

        return sb.toString();
    }

    public String mergeWithOnePointer(String w1, String w2) {
        int m = w1.length();
        int n = w2.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Math.max(m, n); i++) {
            if (i < m) {
                sb.append(w1.charAt(i));
            }
            if (i < n) {
                sb.append(w2.charAt(i));
            }
        }

        return sb.toString();
    }

    public String merge2(String w1, int m, String w2, int n) {
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();

        while (i < w1.length() || j < w2.length()) {
            for (int k = 0; k < m && i < w1.length(); k++) {
                sb.append(w1.charAt(i));
                i++;
            }
            for (int k = 0; k < n && j < w2.length(); k++) {
                sb.append(w2.charAt(j));
                j++;
            }
        }

        return sb.toString();
    }
}
