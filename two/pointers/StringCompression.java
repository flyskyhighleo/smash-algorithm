package two.pointers;

/*
https://leetcode.com/problems/string-compression/description/

Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
Note that group lengths that are 10 or longer will be split into multiple characters in chars.
After you are done modifying the input array, return the new length of the array.
(You must write an algorithm that uses only constant extra space.)
========================================================================================================================
Three pointers.
p points index to override
i points start of group
j points end of group
 */
public class StringCompression {
    public int compressString(char[] chars) {
        int p = 0;
        int i = 0;
        int j = 0;

        while (j < chars.length) {
            while (j < chars.length && chars[j] == chars[i]) {
                j++;
            }
            String count = String.valueOf(j - i);
            chars[p++] = chars[i];
            if (!count.equals("1")) {
                for (int k = 0; k < count.length(); k++) {
                    chars[p++] = count.charAt(k);
                }
            }
            i = j;
        }

        return p;
    }
}