package sliding.window;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/minimum-window-substring/description/
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
character in t (including duplicates) is included in the window. If there is no such substring, return the empty string.
 */
public class MinimumWindowSubstring {
    public String minWindowSubstring(String s, String t) {
        int left = 0;
        int right = 0;
        int start = 0;
        int end = Integer.MAX_VALUE;
        int match = 0;
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        while (right < s.length()) {
            char c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (count.containsKey(c)) {
                if (window.get(c).equals(count.get(c))) {
                    match++;
                }
            }

            while (match == count.size()) {
                if (right - left < end - start) {
                    start = left;
                    end = right;
                }

                char l = s.charAt(left++);

                if (count.containsKey(l)) {
                    if (count.get(l).equals(window.get(l))) {
                        match--;
                    }
                }

                window.put(l, window.get(l) - 1);
            }
        }

        return end == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}
