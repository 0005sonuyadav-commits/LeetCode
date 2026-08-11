class Solution {
    public int[] sortedSquares(int[] nums) {
        int i=0;
        while( i<nums.length){
            nums[i] *= nums[i];
            i++;
        }
        Arrays.sort(nums);
        return nums;
    }
}