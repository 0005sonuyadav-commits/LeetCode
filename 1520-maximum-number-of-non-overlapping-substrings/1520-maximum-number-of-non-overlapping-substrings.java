import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        List<String> result = new ArrayList<>();
        int rightmostEnd = -1;

        for (int i = 0; i < n; i++) {
            // Only attempt expansion if 'i' is the first occurrence of s.charAt(i)
            if (i == first[s.charAt(i) - 'a']) {
                int newRight = getValidEnd(s, i, first, last);
                if (newRight != -1) {
                    // Greedily replace or append based on interval overlap
                    if (i > rightmostEnd) {
                        result.add("");
                    }
                    rightmostEnd = newRight;
                    result.set(result.size() - 1, s.substring(i, rightmostEnd + 1));
                }
            }
        }

        return result;
    }

    private int getValidEnd(String s, int start, int[] first, int[] last) {
        int right = last[s.charAt(start) - 'a'];
        for (int i = start; i <= right; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] < start) return -1; // Overlaps with an earlier range
            right = Math.max(right, last[c]);
        }
        return right;
    }
}