package binary.search;

/*
The initial health of the monster is a positive integer hp. The values of sword cuts and poison effects for each round
are given in arrays cuts and poisons.
The arrays cuts and poisons both have length n, representing that you have a total of n rounds to act.

In the i-th round, if you choose to use the sword, the monster will directly lose cuts[i] health points with no
subsequent effects.
If you choose to use poison in the i-th round, the monster will not lose health during that round, but it will lose
poisons[i] health points in every following round.
Furthermore, the effects of all chosen poisons will stack in subsequent rounds.

In each round, you have to choose only one action: either sword cut or poison.

If you do not manage to kill the monster within the n rounds using direct actions, you can no longer take any new actions.
However, if the monster is poisoned, it will continue to lose health due to poison effects until it dies in the round
when its health is depleted.

Return the minimum number of rounds in which the monster will die.

Constrains:
1 <= n <= 10^5
1 <= hp <= 10^9
1 <= cuts[i], poisons[i] <= 10^9
 */
public class KillAMonster {
    public int roundToKill(int[] cuts, int[] poisons, int hp) {
        int left = 1;
        int right = hp + 1;

        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canKill(cuts, poisons, mid, hp)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private boolean canKill(int[] cuts, int[] poisons, int round, int hp) {
        int n = Math.min(round, cuts.length);
        for (int i = 0; i < n; i++) {
            hp -= Math.max(cuts[i], (round - i - 1) * poisons[i]);
            if (hp <= 0) {
                return true;
            }
        }
        return false;
    }
}
