package two.pointers;

/*
https://leetcode.com/problems/reverse-string/description/
reverse a string in place

https://leetcode.com/problems/reverse-string-ii/description/
Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the
string.
If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k
characters, then reverse the first k characters and leave the other as original.
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
            i++;
            j--;
        }
    }

    public String reverseStringII(String s, int k) {
        char[] arr = s.toCharArray();
        int i = 0;
        while (i < arr.length) {
//            int j = i + k - 1;
//            if (j >= arr.length) {
//                j = arr.length - 1;
//            }
            int j = Math.min(i + k - 1, arr.length - 1);
            reverse(arr, i, j);
            i = i + 2 * k;
        }

        return new String(arr);
    }

    private void reverse(char[] s, int start, int end) {
        while (start < end) {
            char t = s[start];
            s[start] = s[end];
            s[end] = t;
            start++;
            end--;
        }
    }

}
