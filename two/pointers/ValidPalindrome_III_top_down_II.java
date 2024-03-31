package two.pointers;

import java.util.Arrays;

public class ValidPalindrome_III_top_down_II {

    private int[][] memo;

    public boolean isValidPalindrome(String s, int k) {
        memo = new int[s.length()][s.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int minRemoval = dp(s, 0, s.length() - 1);

        return minRemoval <= k;
    }

    private int dp(String s, int i, int j) {
        if (i >= j) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = 0;

        if (s.charAt(i) == s.charAt(j)) {
            res = dp(s, i + 1, j - 1);
        } else {
            int left = dp(s, i + 1, j);
            int right = dp(s, i, j - 1);
            res = Math.min(left, right);
        }

        memo[i][j] = res;

        return res;
    }
}
