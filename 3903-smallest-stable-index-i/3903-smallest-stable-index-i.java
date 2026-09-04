class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = nums.length;

        for(int i=0;i<nums.length;i++){
            if(max<nums[i]){
                max = nums[i];
            }

            int min = Integer.MAX_VALUE;

            for(int j=i;j<end;j++){
                if(min>nums[j]){
                    min= nums[j];
                }
            }
            if(max-min<=k){
                return i;
            }
        }

        

        // int mid = start +(end-start)/2;
        // int idx = 0;
        

        // for(int i=0;i<=mid;i++){
        //     for(int j=mid+1;j<end;j++){
        //         if(k==0){
        //             return -1;
        //         }
        //         else if(max - nums[j] <= k){
        //             idx =  j;
        //         }else{
        //             return -1;
        //         }
        //     }
        // }
        // return idx;
        return -1;
    }
}