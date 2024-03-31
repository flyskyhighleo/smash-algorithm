package two.pointers;

/*
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the
lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
************************************************************************************************************************
Clarification:
Node p and Node q are in the tree.

If p and q are on same level, traverse upwards, the meet point is the LCA.
However, p and q may not on the same level.

a0 -> a1 -> a2 -> a3
                    \
                      c0 -> c1 -> null
                    /
            b0 -> b1

given a0 and b0, goal is to find c0
move p and q to their parents, when reaching null, set p or q back to the other's origin .
and continue move p and q until they meet.
The meet point is LCA.

 */
public class LCAIII {

    public TreeNode lowestCommonAncestorIII(TreeNode p, TreeNode q) {
        TreeNode pOrigin = p;
        TreeNode qOrigin = q;

        while (p != q) {
            if (p == null) {
                p = qOrigin;
            } else {
                p = p.parent;
            }

            if (q == null) {
                q = pOrigin;
            } else {
                q = q.parent;
            }
        }

        return p;
    }

    private class TreeNode {
        int val;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
    }
}
