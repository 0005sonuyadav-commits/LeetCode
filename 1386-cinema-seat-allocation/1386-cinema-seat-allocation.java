

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        Map<Integer, Integer> map = new HashMap<>();

        // Store reserved seats for each row using bitmask
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            int mask = map.getOrDefault(row, 0);

            // Seat 1 -> bit 0, seat 2 -> bit 1, ...
            mask |= (1 << (col - 1));

            map.put(row, mask);
        }

        int ans = 0;

        // Rows without any reservation can always fit 2 groups
        ans += (n - map.size()) * 2;

        // Check only rows having reservations
        for (int mask : map.values()) {

            // 2,3,4,5
            boolean left = (mask & (1 << 1)) == 0 &&
                           (mask & (1 << 2)) == 0 &&
                           (mask & (1 << 3)) == 0 &&
                           (mask & (1 << 4)) == 0;

            // 4,5,6,7
            boolean middle = (mask & (1 << 3)) == 0 &&
                             (mask & (1 << 4)) == 0 &&
                             (mask & (1 << 5)) == 0 &&
                             (mask & (1 << 6)) == 0;

            // 6,7,8,9
            boolean right = (mask & (1 << 5)) == 0 &&
                            (mask & (1 << 6)) == 0 &&
                            (mask & (1 << 7)) == 0 &&
                            (mask & (1 << 8)) == 0;

            if (left && right) {
                ans += 2;
            } else if (left || middle || right) {
                ans += 1;
            }
        }

        return ans;
    }
}