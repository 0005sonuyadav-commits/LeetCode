class Solution {

    int[] left;
    int[] right;
    int[] best;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {

        int n = str.length();

        s = str.toCharArray();

        left = new int[4 * n];
        right = new int[4 * n];
        best = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];

            s[index] = queryCharacters.charAt(i);

            update(1, 0, n - 1, index);

            ans[i] = best[1];
        }

        return ans;
    }

    void build(int node, int l, int r) {

        if (l == r) {
            left[node] = 1;
            right[node] = 1;
            best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        merge(node, l, r);
    }

    void update(int node, int l, int r, int index) {

        if (l == r) {
            left[node] = 1;
            right[node] = 1;
            best[node] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        merge(node, l, r);
    }

    void merge(int node, int l, int r) {

        int mid = (l + r) / 2;

        int leftNode = node * 2;
        int rightNode = node * 2 + 1;

        left[node] = left[leftNode];
        right[node] = right[rightNode];

        best[node] = Math.max(best[leftNode], best[rightNode]);

        // Check whether the two parts can be joined
        if (s[mid] == s[mid + 1]) {

            best[node] = Math.max(
                best[node],
                right[leftNode] + left[rightNode]
            );

            // Entire left half has the same character
            if (right[leftNode] == mid - l + 1) {
                left[node] += left[rightNode];
            }

            // Entire right half has the same character
            if (left[rightNode] == r - mid) {
                right[node] += right[leftNode];
            }
        }
    }
}