import java.util.TreeMap;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + (long) k2;
        
        TreeMap<Integer, Long> map = new TreeMap<>();
        long initialSumDiff = 0;
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0) {
                map.put(diff, map.getOrDefault(diff, 0L) + 1L);
            }
            initialSumDiff += (long) diff * diff;
        }
        
        // If total operations can reduce all differences to 0
        long totalDiffSum = 0;
        for (int key : map.keySet()) { // Fixed from 'long key' to 'int key'
            totalDiffSum += (long) key * map.get(key);
        }
        if (totalDiffSum <= totalOps) {
            return 0;
        }
        
        // Greedily reduce the largest differences
        while (totalOps > 0 && !map.isEmpty()) {
            int maxDiff = map.lastKey();
            long count = map.get(maxDiff);
            
            long opsNeeded = count;
            
            if (totalOps >= opsNeeded) {
                totalOps -= opsNeeded;
                map.remove(maxDiff);
                map.put(maxDiff - 1, map.getOrDefault(maxDiff - 1, 0L) + count);
            } else {
                map.put(maxDiff, count - totalOps);
                map.put(maxDiff - 1, map.getOrDefault(maxDiff - 1, 0L) + totalOps);
                totalOps = 0;
            }
        }
        
        long result = 0;
        for (var entry : map.entrySet()) {
            long diff = entry.getKey();
            long count = entry.getValue();
            result += count * diff * diff;
        }
        
        return result;
    }
}