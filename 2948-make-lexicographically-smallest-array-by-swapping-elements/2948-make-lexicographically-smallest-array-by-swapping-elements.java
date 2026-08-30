import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Pair values with original indices and sort by value
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int i = 0;

        // Step 2: Form connected components
        while (i < n) {
            int j = i;
            List<Integer> indices = new ArrayList<>();
            
            // Find continuous block where adjacent diff <= limit
            while (j < n && (j == i || pairs[j][0] - pairs[j - 1][0] <= limit)) {
                indices.add(pairs[j][1]);
                j++;
            }
            
            // Step 3: Sort original indices and place sorted values back
            Collections.sort(indices);
            for (int k = 0; k < indices.size(); k++) {
                result[indices.get(k)] = pairs[i + k][0];
            }
            
            i = j;
        }

        return result;
    }
}