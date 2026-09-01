import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int sr = -1, sc = -1;
        
        List<int[]> litters = new ArrayList<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sr = r;
                    sc = c;
                } else if (ch == 'L') {
                    litters.add(new int[]{r, c});
                }
            }
        }
        
        int numLitter = litters.size();
        int targetMask = (1 << numLitter) - 1;
        
        if (targetMask == 0) return 0; // No litter to collect

        // bestEnergy[r][c][mask] stores maximum remaining energy for a given state
        int[][][] bestEnergy = new int[m][n][1 << numLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }

        // Queue holds [r, c, mask, energy]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0, energy});
        bestEnergy[sr][sc][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0], c = curr[1], mask = curr[2], currEnergy = curr[3];

                if (mask == targetMask) {
                    return steps;
                }

                if (currEnergy == 0) continue; // Out of energy to move further

                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char cell = classroom[nr].charAt(nc);
                    if (cell == 'X') continue;

                    int nextEnergy = currEnergy - 1;
                    int nextMask = mask;

                    if (cell == 'R') {
                        nextEnergy = energy;
                    } else if (cell == 'L') {
                        for (int idx = 0; idx < numLitter; idx++) {
                            if (litters.get(idx)[0] == nr && litters.get(idx)[1] == nc) {
                                nextMask |= (1 << idx);
                                break;
                            }
                        }
                    }

                    if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                        bestEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}