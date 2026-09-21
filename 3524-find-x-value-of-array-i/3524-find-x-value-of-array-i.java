class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int mod = num % k;

            nextDp[mod]++;

            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextRem = (r * mod) % k;
                    nextDp[nextRem] += dp[r];
                }
            }

            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return result;
    }
}