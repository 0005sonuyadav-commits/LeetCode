class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int result = Integer.MAX_VALUE;
        
        int currentSum = 0;
        int left = 0;
        int minSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            while (currentSum > target) {
                currentSum -= arr[left];
                left++;
            }
            
            if (currentSum == target) {
                int len = right - left + 1;
                
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, len + minLen[left - 1]);
                }
                
                minSoFar = Math.min(minSoFar, len);
            }
            
            minLen[right] = minSoFar;
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}