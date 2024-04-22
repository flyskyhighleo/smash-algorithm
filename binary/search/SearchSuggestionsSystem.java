package binary.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/search-suggestions-system/description/

You are given an array of strings products and a string searchWord.
Design a system that suggests at most three product names from products after each character of searchWord is typed.
Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix
return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output:
[
    ["mobile","moneypot","monitor"],
    ["mobile","moneypot","monitor"],
    ["mouse","mousepad"],
    ["mouse","mousepad"],
    ["mouse","mousepad"]
]
========================================================================================================================
Solution 1: Binary search
1. sort the input in increasing order.
2. for each character entered, search the left most element that >= than the target
3. select at most three words that start with the entered characters.
 */
public class SearchSuggestionsSystem {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        int m = products.length;
        int n = searchWord.length();
        List<List<String>> res = new ArrayList<>();

        Arrays.sort(products);

        for (int i = 0; i < n; i++) {
            String key = searchWord.substring(0, i + 1);
            List<String> subres = new ArrayList<>();
            int start = search(products, key);
            if (start == -1 || !products[start].startsWith(key)) {
                for (int k = start; k < n; k++) {
                    res.add(subres);
                }
                break;
            }
            for (int j = start; j < m && j - start < 3 && products[i].startsWith(key); j++) {
                subres.add(products[j]);
            }
        }

        return res;
    }

    private int search(String[] products, String key) {
        int left = 0;
        int right = products.length - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].compareTo(key) >= 0) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
