class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        double[] dp = new double[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            char charS = s.charAt(i - 1);
            for (int j = n; j >= 1; j--) {
                if (charS == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return (int) dp[n];
    }
}