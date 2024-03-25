package two.pointers.fast_and_slow;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/dot-product-of-two-sparse-vectors/
Given two sparse vectors, compute their dot product.
Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the
 dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?
************************************************************************************************************************
v1 = [0,0,0,1,0,0,2,0]
v2 = [0,2,0,2,0,0,0,0]

with hash map. key: index, value: non-zero number
v1 -> {{3,1},{6,2}}
v2 -> {{1,2},{3,2}}
************************************************************************************************************************
Approach two: two pointers and save pair(index, number) of non-zero numbers

 */
public class SparseVector {
    /*
    Map<Integer, Integer> indexToNum;
    public SparseVector(int[] nums) {
        this.indexToNum = new HashMap<>();
        for (int i = 0 ; i < nums.length; i++) {
            if (nums[i] != 0) {
                indexToNum.put(i, nums[i]);
            }
        }
    }

    public int dotProduct(SparseVector vec) {
        int prod = 0;
        Map<Integer, Integer> otherIndexToNum = vec.indexToNum;
        for (Map.Entry<Integer, Integer> entry : this.indexToNum.entrySet()) {
            int index = entry.getKey();
            int num = entry.getValue();

            if (otherIndexToNum.containsKey(index)) {
                prod += num * otherIndexToNum.get(index);
            }
        }

        return prod;
    }
     */

    // approach two: two pointers and pair(index, number) in a list
    List<int[]> nonZeros;
    public SparseVector(int[] nums) {
        this.nonZeros = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeros.add(new int[]{i, nums[i]});
            }
        }
    }

    public int dotProduct(SparseVector vec) {
        int p = 0;
        int q = 0;
        int m = this.nonZeros.size();
        int n = vec.nonZeros.size();
        int prod = 0;
        List<int[]> otherNonZeros = vec.nonZeros;

        while (p < m && q < n) {
            if (nonZeros.get(p)[0] == otherNonZeros.get(q)[0]) {
                prod += (nonZeros.get(p)[1] * nonZeros.get(q)[1]);
            } else {
                if (nonZeros.get(p)[0] < otherNonZeros.get(q)[0]) {
                    p++;
                } else {
                    q++;
                }
            }
        }

        return prod;
    }
}
