import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }

        // At most 1 character can have an odd count
        if (oddCount > 1) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        String best = null;

        // Try matching prefix of length `i` (0 <= i <= halfLen)
        for (int i = halfLen; i >= 0; i--) {
            int[] currentHalfCount = halfCount.clone();
            char[] prefix = new char[halfLen];
            boolean validPrefix = true;

            // Build the matching prefix of length i
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (currentHalfCount[c] > 0) {
                    prefix[j] = target.charAt(j);
                    currentHalfCount[c]--;
                } else {
                    validPrefix = false;
                    break;
                }
            }

            if (!validPrefix) continue;

            // Case 1: Make position `i` strictly greater than `target[i]`
            if (i < halfLen) {
                int targetChar = target.charAt(i) - 'a';
                for (int nextChar = targetChar + 1; nextChar < 26; nextChar++) {
                    if (currentHalfCount[nextChar] > 0) {
                        char[] candidateHalf = prefix.clone();
                        int[] avail = currentHalfCount.clone();

                        candidateHalf[i] = (char) ('a' + nextChar);
                        avail[nextChar]--;

                        // Fill the rest of the half greedily with smallest available chars
                        int ptr = i + 1;
                        for (int c = 0; c < 26; c++) {
                            while (avail[c] > 0) {
                                candidateHalf[ptr++] = (char) ('a' + c);
                                avail[c]--;
                            }
                        }

                        String cand = buildPalindrome(candidateHalf, oddChar, n);
                        if (cand.compareTo(target) > 0) {
                            if (best == null || cand.compareTo(best) < 0) {
                                best = cand;
                            }
                        }
                    }
                }
            } 
            // Case 2: Exact match on the first half (i == halfLen)
            else {
                String cand = buildPalindrome(prefix, oddChar, n);
                if (cand.compareTo(target) > 0) {
                    if (best == null || cand.compareTo(best) < 0) {
                        best = cand;
                    }
                }
            }
        }

        return best == null ? "" : best;
    }

    private String buildPalindrome(char[] half, int oddChar, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        if (n % 2 != 0) {
            sb.append((char) ('a' + oddChar));
        }
        for (int j = half.length - 1; j >= 0; j--) {
            sb.append(half[j]);
        }
        return sb.toString();
    }
}