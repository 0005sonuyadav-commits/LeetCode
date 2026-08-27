class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try prefixes of target matching length from n-1 down to 0
        for (int matchLen = n - 1; matchLen >= 0; matchLen--) {
            int[] freq = count.clone();
            boolean possible = true;
            
            // Check if we can form target[0...matchLen-1]
            for (int i = 0; i < matchLen; i++) {
                char c = target.charAt(i);
                if (--freq[c - 'a'] < 0) {
                    possible = false;
                    break;
                }
            }
            if (!possible) continue;

            // Find smallest character greater than target[matchLen] at position matchLen
            int targetChar = target.charAt(matchLen) - 'a';
            int nextChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    nextChar = c;
                    break;
                }
            }

            if (nextChar != -1) {
                // Construct the valid result
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, matchLen);
                sb.append((char) ('a' + nextChar));
                freq[nextChar]--;

                // Append remaining available characters in sorted (ascending) order
                for (int c = 0; c < 26; c++) {
                    while (freq[c]-- > 0) {
                        sb.append((char) ('a' + c));
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}