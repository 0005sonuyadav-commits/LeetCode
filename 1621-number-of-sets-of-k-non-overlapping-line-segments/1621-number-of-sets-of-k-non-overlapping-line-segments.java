class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        long N = n + k - 1;
        long K = 2 * k;

        if (K > N) return 0;

        long num = 1;
        long den = 1;

        for (int i = 1; i <= K; i++) {
            num = (num * (N - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        // Modular Inverse of denominator using Fermat's Little Theorem
        return (int) ((num * modInverse(den, MOD)) % MOD);
    }

    private long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp /= 2;
        }
        return res;
    }
}