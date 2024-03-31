package two.pointers;

import java.util.HashMap;
import java.util.Map;

public class ValidPalindrome_III_top_down_I {

    private Map<String, Boolean> memo = new HashMap<>();
    public boolean isValidPalindrome(String s, int k) {
        return dp(s, 0, s.length() - 1, k, 0);
    }

    private boolean dp(String s, int i, int j, int k, int removal) {
        if (removal > k) {
            return false;
        }

        if (i >= j) {
            return true;
        }

        String key = i + "," + j + "removal";

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean res = false;

        if (s.charAt(i) == s.charAt(j)) {
            res = dp(s, i + 1, j - 1, k, removal);
        } else {
            res = dp(s, i + 1, j, k, removal + 1) || dp(s, i, j - 1, k, removal);
        }

        memo.put(key, res);

        return res;
    }
}
