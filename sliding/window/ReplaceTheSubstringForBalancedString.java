package sliding.window;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/replace-the-substring-for-balanced-string/description/
You are given a string s of length n containing only four kinds of characters: 'Q', 'W', 'E', and 'R'.
A string is said to be balanced if each of its characters appears n / 4 times where n is the length of the string.
Return the minimum length of the substring that can be replaced with any other string of the same length to make s balanced.
If s is already balanced, return 0.
========================================================================================================================
count map : Q = W = E = R = n / 4
s = "Q Q W E R E W E W Q W E" => get count
calculate element count to replace. Q:0, W:1 (4 - 3), E:1 (4-3), R:0 (1-3)
now we have neededCountMap W:1 (4 - 3), E:1 (4-3)
find a minimum substring in s that covers all letters in needed count map.

 */
public class ReplaceTheSubstringForBalancedString {
    public int balancedString(String s) {
        int n = s.length();
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> needed = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();
            if (freq - n / 4 > 0) {
                needed.put(c, freq - n / 4);
            }
        }

        int left = 0;
        int right = 0;
        int start = 0;
        int end = 0;
        int match = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < n) {
            char c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (needed.containsKey(c)) {
                if (window.get(c).equals(needed.get(c))) {
                    match++;
                }
            }

            while (match == needed.size()) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }
                char l = s.charAt(left++);
                if (needed.containsKey(l)) {
                    if (window.get(l).equals(needed.get(l))) {
                        match--;
                    }
                }
                window.put(c, window.get(c) - 1);
            }
        }

        return end - start;
    }
}
