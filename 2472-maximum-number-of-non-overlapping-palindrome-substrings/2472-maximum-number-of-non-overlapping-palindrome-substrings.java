class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int i = 0;

        while (i < n) {
            // Check if there is a valid palindrome of length k starting at i
            if (i + k <= n && isPalindrome(s, i, i + k - 1)) {
                count++;
                i += k; // Skip the palindrome length to avoid overlap
            } 
            // Check if there is a valid palindrome of length k + 1 starting at i
            else if (i + k + 1 <= n && isPalindrome(s, i, i + k)) {
                count++;
                i += k + 1; // Skip the palindrome length
            } 
            else {
                i++; // Advance to the next starting position
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}