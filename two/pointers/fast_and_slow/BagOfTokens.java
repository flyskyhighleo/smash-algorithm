package two.pointers.fast_and_slow;

import java.util.Arrays;

/*
https://leetcode.com/problems/bag-of-tokens/description/

You start with an initial power of power, an initial score of 0, and a bag of tokens given as an integer array tokens,
where each tokens[i] denotes the value of token_i.

Your goal is to maximize the total score by strategically playing these tokens. In one move, you can play an unplayed
token in one of the two ways (but not both for the same token):

Face-up: If your current power is at least tokens[i], you may play tokeni, losing tokens[i] power and gaining 1 score.
Face-down: If your current score is at least 1, you may play tokeni, gaining tokens[i] power and losing 1 score.
Return the maximum possible score you can achieve after playing any number of tokens.
========================================================================================================================
Greedy and Two pointers
Sort the tokens, maintain power.
Strategy, face up as much as possible
Time: O(N*logN)
 */
public class BagOfTokens {
    public int maxScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0;
        int j = tokens.length - 1;
        int score = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                score += 1;
                power -= tokens[i];
                i++;
            } else {
                if (i < j && score >= 1) {
                    power += tokens[j];
                    score -= 1;
                }
                j--;
            }
        }
        return score;
    }
}
