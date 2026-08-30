class Solution {
    public int minimumDeletions(int[] nums) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        int minidx = -1;
        int maxidx =-1;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>min){
                min = nums[i];
                minidx = i;
            }
            if(nums[i]<max){
                max = nums[i];  
                maxidx =i; 
            }
        }

        int left = Math.max(minidx , maxidx)+1;

        int right = nums.length - Math.min(minidx , maxidx);

        int both = (Math.min(minidx , maxidx)+1) + (nums.length - Math.max(minidx , maxidx));

        return Math.min(left , Math.min(right,both));

    }
}