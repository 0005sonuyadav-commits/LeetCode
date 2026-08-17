class Solution {

    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        // DP table
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Prefix sum
        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    private int solve(int[] stoneValue, int left, int right) {

        // Only one stone
        if (left == right) {
            return 0;
        }

        // Already calculated
        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int answer = 0;

        // Try every possible split
        for (int mid = left; mid < right; mid++) {

            // Sum from left -> mid
            int leftsum = prefix[mid + 1] - prefix[left];

            // Sum from mid + 1 -> right
            int rightsum = prefix[right + 1] - prefix[mid + 1];

            // Left side is smaller
            if (leftsum < rightsum) {

                answer = Math.max(
                    answer,
                    leftsum + solve(stoneValue, left, mid)
                );

            }

            // Right side is smaller
            else if (rightsum < leftsum) {

                answer = Math.max(
                    answer,
                    rightsum + solve(stoneValue, mid + 1, right)
                );

            }

            // Both sides are equal
            else {

                answer = Math.max(
                    answer,
                    leftsum + solve(stoneValue, left, mid)
                );

                answer = Math.max(
                    answer,
                    rightsum + solve(stoneValue, mid + 1, right)
                );
            }
        }

        // Save result
        dp[left][right] = answer;

        return answer;
    }
}