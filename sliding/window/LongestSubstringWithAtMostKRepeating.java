package sliding.window;

/*
https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct
characters.
 */
public class LongestSubstringWithAtMostKRepeating {
    public int longestAtMostK(String s, int k) {
        int left = 0;
        int right = 0;
        int distinct = 0;
        int res = 0;
        int[] cnts = new int[256];

        while (right < s.length()) {
            char r = s.charAt(right++);
            cnts[r]++;
            if (cnts[r] == 1) {
                distinct++;
            }

            while (distinct > k) {
                char l = s.charAt(left++);
                if (cnts[l] == 1) {
                    distinct--;
                }
                cnts[l]--;
            }

            res = Math.max(res, right - left);
        }

        return res;
    }
}
