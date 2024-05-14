package unionfind;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/similar-string-groups/description/
Two strings, X and Y, are considered similar if either they are identical or we can make them equivalent by swapping at
most two letters (in distinct positions) within the string X.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star"
is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and
"arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group
if and only if it is similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. How many
groups are there?
 */
public class SimilarStringGroups {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    uf.union(i, j);
                }
            }
        }

        return uf.getCount();
    }

    private boolean isSimilar(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Set<Character> charSet = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            char b = s.charAt(i);
            if (a != b) {
                charSet.add(a);
                charSet.add(b);
            }

            if (charSet.size() > 2) {
                return false;
            }
        }

        return charSet.isEmpty() || charSet.size() == 2;
    }

    class UnionFind {
        private int count;
        private int[] parent;

        public UnionFind(int n) {
            this.count = n;
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

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                parent[rootP] = rootQ;
                count--;
            }
        }

        public int getCount() {
            return this.count;
        }
    }
}
