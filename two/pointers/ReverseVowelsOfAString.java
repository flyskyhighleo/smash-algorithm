package two.pointers;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/reverse-vowels-of-a-string/description/
Given a string s, reverse only all the vowels in the string and return it.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.
 */
public class ReverseVowelsOfAString {

    private static final Set<Character> VOWELS = new HashSet<>();
    static {
        VOWELS.add('a');
        VOWELS.add('e');
        VOWELS.add('i');
        VOWELS.add('o');
        VOWELS.add('u');
        VOWELS.add('A');
        VOWELS.add('E');
        VOWELS.add('I');
        VOWELS.add('O');
        VOWELS.add('U');
    }
    public String reverseVowels(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] chars = s.toCharArray();
        while (l < r) {
            while (l < r && !VOWELS.contains(chars[l])) {
                l++;
            }
            while (l < r && !VOWELS.contains(chars[r])) {
                r--;
            }

            char t = chars[l];
            chars[l] = chars[r];
            chars[r] = t;
        }
        return new String(chars);
    }

}
