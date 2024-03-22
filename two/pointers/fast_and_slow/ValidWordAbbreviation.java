package two.pointers.fast_and_slow;

/*
https://leetcode.com/problems/valid-word-abbreviation/description/
A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths.
The lengths should not have leading zeros.
For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
The following are not valid abbreviations:

"s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
"s010n" (has leading zeros)
"s0ubstitution" (replaces an empty substring)
Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.

A substring is a contiguous non-empty sequence of characters within a string.
================================================================================================================
two pointers, p and q in string s and abbr
maintain a variable offset
offset = value extracted from abbr
when q is a digit,
    continue calculating the offset until q is a letter
when q is a letter,
    move p offset steps
    if p is out of string range, return false
    compare p and q

when exit the loop,
    check if p is in the range of string.
        if yes, return false

at the end return true
================================================================================================================
This question is all about two pointers strategy.
Note:
    we move first, then compare.
    Be careful about range. Always think about edge cases.
    When moving a pointer, we consider
        1. is it still in the range? What's the next step if it's still in the range.
        2. is it out of the range? What will happen if it's out of range.
 */
public class ValidWordAbbreviation {
    public boolean canMatch(String word, String abbr) {
        int offset = 0;
        int p = 0;

        for (int q = 0; q < abbr.length(); q++) {
            if (Character.isDigit(abbr.charAt(q))) {
                if (offset == 0 && abbr.charAt(q) == '0') {
                    return false;
                }
                offset = offset * 10 + (abbr.charAt(q) - '0');
            } else {
                p = p + offset;
                if (p >= word.length()) {
                    return false;
                }
                if (word.charAt(p) != abbr.charAt(q)) {
                    return false;
                }
                p += 1;
                offset = 0;
            }
        }

        return p + offset == word.length();
    }
}
