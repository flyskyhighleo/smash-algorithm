package sliding.window;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each
character in this substring is greater than or equal to k.
if no such substring exists, return 0.
 */
public class LongestSubstringWithAtLeastKRepeating {
    public int longestSubstringWithAtLeastK(String s, int k) {
        int res = 0;
        for (int i = 1; i <= 26; i++) {
            res = Math.max(res, longestKLimit(s, k, i));
        }

        return res;
    }

    private int longestKLimit(String s, int k, int limit) {
        int left = 0;
        int right = 0;
        int satisfies = 0;
        int unique = 0;
        int res = 0;

        Map<Character, Integer> count = new HashMap<>();

        while (right < s.length()) {
            char r = s.charAt(right++);
            count.put(r, count.getOrDefault(r, 0) + 1);
            if (count.get(r) == 1) {
                unique++;
            }
            if (count.get(r) == k) {
                satisfies++;
            }

            while (unique > limit) {
                char l = s.charAt(left++);
                if (count.get(l) == 1) {
                    unique--;
                }
                if (count.get(l) == k) {
                    satisfies--;
                }
                count.put(l, count.get(l) - 1);
            }

            if (satisfies == limit) {
                res = Math.max(res, right - left);
            }
        }

        return res;
    }
}
