import java.util.*;

class Solution {

    int n, k;
    int[][] pref;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        pref = new int[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.pref[x];
        }

        return ans;
    }

    void build(int node, int l, int r, int[] nums) {

        if (l == r) {
            int rem = nums[l] % k;

            prod[node] = rem;
            pref[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node);
    }

    void update(int node, int l, int r, int index, int value) {

        if (l == r) {
            Arrays.fill(pref[node], 0);

            int rem = value % k;

            prod[node] = rem;
            pref[node][rem] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        merge(node);
    }

    void merge(int node) {

        int left = node * 2;
        int right = node * 2 + 1;

        Arrays.fill(pref[node], 0);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            pref[node][r] += pref[left][r];
        }

        // Complete left + prefix of right
        for (int r = 0; r < k; r++) {
            int newRem = (prod[left] * r) % k;

            pref[node][newRem] += pref[right][r];
        }

        prod[node] = (prod[left] * prod[right]) % k;
    }

    Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {

            Node res = new Node(k);

            res.prod = prod[node];

            for (int i = 0; i < k; i++) {
                res.pref[i] = pref[node][i];
            }

            return res;
        }

        int mid = (l + r) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return mergeNodes(left, right);
    }

    Node mergeNodes(Node left, Node right) {

        Node res = new Node(k);

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.pref[r] += left.pref[r];
        }

        // Complete left + prefix of right
        for (int r = 0; r < k; r++) {

            int newRem = (left.prod * r) % k;

            res.pref[newRem] += right.pref[r];
        }

        res.prod = (left.prod * right.prod) % k;

        return res;
    }

    static class Node {

        int[] pref;
        int prod;

        Node(int k) {
            pref = new int[k];
        }
    }
}