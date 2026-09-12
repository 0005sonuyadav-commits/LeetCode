import java.util.*;

class Solution {
    private static class Interval {
        int l, r, weight, id;
        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] arr = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            arr[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        // Sort intervals by right end point
        Arrays.sort(arr, (a, b) -> Integer.compare(a.r, b.r));

        // dp[k][i] stores {maxScore, list of original indices}
        // using 1-based indexing for convenience
        long[][] dpScore = new long[5][n + 1];
        List<Integer>[][] dpIndices = new List[5][n + 1];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dpIndices[k][i] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            Interval curr = arr[i - 1];
            
            // Binary search to find last non-overlapping interval
            int low = 0, high = i - 2, prevIdx = -1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid].r < curr.l) {
                    prevIdx = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Do not pick interval arr[i - 1]
                long score1 = dpScore[k][i - 1];
                List<Integer> indices1 = dpIndices[k][i - 1];

                // Option 2: Pick interval arr[i - 1]
                long score2 = curr.weight;
                List<Integer> indices2 = new ArrayList<>();
                if (prevIdx != -1) {
                    score2 += dpScore[k - 1][prevIdx + 1];
                    indices2.addAll(dpIndices[k - 1][prevIdx + 1]);
                }
                indices2.add(curr.id);
                Collections.sort(indices2);

                if (score1 > score2) {
                    dpScore[k][i] = score1;
                    dpIndices[k][i] = indices1;
                } else if (score2 > score1) {
                    dpScore[k][i] = score2;
                    dpIndices[k][i] = indices2;
                } else {
                    // Tie-breaker: choose lexicographically smaller list of indices
                    dpScore[k][i] = score1;
                    if (compareLists(indices2, indices1) < 0) {
                        dpIndices[k][i] = indices2;
                    } else {
                        dpIndices[k][i] = indices1;
                    }
                }
            }
        }

        // Find overall best combination among 1..4 intervals
        long maxScore = 0;
        List<Integer> bestIndices = new ArrayList<>();

        for (int k = 1; k <= 4; k++) {
            if (dpScore[k][n] > maxScore) {
                maxScore = dpScore[k][n];
                bestIndices = dpIndices[k][n];
            } else if (dpScore[k][n] == maxScore && maxScore > 0) {
                if (compareLists(dpIndices[k][n], bestIndices) < 0) {
                    bestIndices = dpIndices[k][n];
                }
            }
        }

        int[] result = new int[bestIndices.size()];
        for (int i = 0; i < bestIndices.size(); i++) {
            result[i] = bestIndices.get(i);
        }
        return result;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(a.size(), b.size());
    }
}