class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        int MOD = 1_000_000_007;
        
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: empty string
        
        int[] last = new int[26];
        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }
        
        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int index = ch - 'a';
            
            dp[i] = (2 * dp[i - 1]) % MOD;
            
            if (last[index] != -1) {
                dp[i] = (dp[i] - dp[last[index] - 1] + MOD) % MOD;
            }
            
            last[index] = i;
        }
        
        // Subtract 1 to exclude the empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}