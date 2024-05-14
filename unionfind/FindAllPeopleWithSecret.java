package unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/find-all-people-with-secret/description/

You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D
integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at
timei. A person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.

Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared
every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has
the secret at timei, then they will share the secret with person yi, and vice versa.

The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other
meetings within the same time frame.

Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer
in any order.
 */
public class FindAllPeopleWithSecret {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        UnionFind uf = new UnionFind(n);
        uf.setKnow(0);
        uf.union(0, firstPerson);

        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        int i = 0;

        while (i < meetings.length) {
            int j = i;
            while (j < meetings.length && meetings[i][2] == meetings[j][2]) {
                j++;
            }

            for (int k = i; k < j; k++) {
                uf.union(meetings[k][0], meetings[k][1]);
            }

            for (int k = i; k < j; k++) {
                if (!uf.getKnow(meetings[k][0])) {
                    uf.unUnion(meetings[k][0]);
                }
                if (!uf.getKnow(meetings[k][1])) {
                    uf.unUnion(meetings[k][1]);
                }
            }

            i = j;
        }

        List<Integer> res = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            if (uf.getKnow(p)) {
                res.add(p);
            }
        }

        return res;
    }

    class UnionFind {
        private int count;
        private int[] parent;
        private boolean[] know;

        public UnionFind(int n) {
            this.count = n;
            this.know = new boolean[n];
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--;
                know[rootQ] |= know[rootP];
            }
        }

        public void unUnion(int x) {
            parent[x] = x;
        }

        public int getCount() {
            return this.count;
        }

        public void setKnow(int x) {
            this.know[x] = true;
        }

        public boolean getKnow(int x) {
            return this.know[find(x)];
        }
    }
}
