package two.pointers;

/*
https://leetcode.com/problems/backspace-string-compare/description/
Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a
backspace character.
Note that after backspacing an empty text, the text will continue empty.
========================================================================================================================
Approach 1: with stack
push characters into stack. Pop the top element when the current character is '#'
Time: O(n), space: O(n)

Solution 2: iterate right to left. two pointers
iterate string s
    if the current letter is '#', skip + 1, i - 1
    else if skip > 0, skip - 1, i - 1
    else break;
The pointer now stay at a letter that needs to keep.
Same for string t.

compare the current letter in s and t.

Trick:
when use || as loop end condition,
use
        if ((i >= 0) != (j >= 0)) {
            return false;
        }
to check if both i and j are out of boundary or still in the range.
 */

import java.util.Stack;
public class BackspaceStringCompare {
    public boolean compareWithStack(String s, String t) {
        Stack<Character> stackS = writeToStack(s);
        Stack<Character> stackT = writeToStack(t);

        while (!stackS.isEmpty() && !stackT.isEmpty()) {
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }

        return stackS.isEmpty() && stackT.isEmpty();
    }

    private Stack<Character> writeToStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }
        return stack;
    }

    public boolean compare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS += 1;
                    i -= 1;
                } else if (skipS > 0) {
                    i -= 1;
                    skipS -= 1;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT += 1;
                    j -= 1;
                } else if (skipT > 0) {
                    j -= 1;
                    skipT -= 1;
                } else {
                    break;
                }
            }

            if (i >= 0 && j >= 0) {
                if (s.charAt(i) != t.charAt(j)) {
                    return false;
                }
            }

            if ((i >= 0) != (j >= 0)) {
                return false;
            }

            i -= 1;
            j -= 1;
        }

        return false;
    }
}
