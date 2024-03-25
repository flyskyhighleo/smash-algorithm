package two.pointers.left_and_right;

/*
Define the dp array
 dp[i][j] is the min times of removal to make string s[i...j] a valid palindrome.

i and j is in the mid of string
dp[mid][mid] = 0
dp[i][j] <- dp[i+1][j-1]
         <- dp[i+1][j]
         <- dp[i][j-1]
goal is to get dp[0][n-1]

          C A
          B D
base case
when i == j, dp[i][j] = 0, which means zero removal to get s[i,j] valid palindrome

    0, 1, 2, 3, 4, 5
  0 0              X
  1    0
  2       0
  3          0
  4             0
  5                0

Traverse directions
    1. bottom-up, left-right
    for (int i = n - 2; i >= 0; i--)
        for (int j = i + 1; j < n; j++)

    2. by diagonal
    for (int l = 1; l < n ; l++)
        for (i = 0; i < n - l; i++)
            int j = i + l


 */
public class ValidPalindrome_III_bottom_up {
    public boolean isValidPalindrome(String s, int k) {
        int minRemoval = minStepsToValid(s);
        return minRemoval <= k;
    }

    private int minStepsToValid(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // traverse bot-up, left-right
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(1 + dp[i + 1][j], 1 + dp[i][j - 1]);
                }
            }
        }

        // traverse by diagonal
        for (int l = 1; l < n; l++) {
            for (int i = 0; i < n - l; i++) {
                int j = i + l;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(1 + dp[i + 1][j], 1 + dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
