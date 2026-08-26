class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int onesCount = 0;
        int left = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }

            // Shrink window from left to make it as minimal as possible while keeping k ones
            while (onesCount == k) {
                String currentSub = s.substring(left, right + 1);

                if (result.isEmpty() || currentSub.length() < result.length() || 
                   (currentSub.length() == result.length() && currentSub.compareTo(result) < 0)) {
                    result = currentSub;
                }

                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
        }

        return result;
    }
}