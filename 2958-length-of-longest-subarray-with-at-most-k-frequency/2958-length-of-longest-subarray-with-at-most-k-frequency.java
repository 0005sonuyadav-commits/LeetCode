class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int max =0;
        HashMap<Integer, Integer> map = new HashMap<>();

        int left =0;
        for(int right=0;right<n;right++){

            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.get(nums[right]) > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}