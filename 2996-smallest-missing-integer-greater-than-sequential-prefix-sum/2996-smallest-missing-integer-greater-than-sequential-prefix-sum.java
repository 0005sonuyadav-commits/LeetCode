class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int sum = nums[0];

        for(int i =1;i<n;i++){
            if(nums[i] == nums[i-1]+1){
                sum+=nums[i];
            }else{
                break;
            }
        }

        
        while(true){
            boolean found_num = false;

            for(int num : nums){
                if(num == sum){
                   found_num = true;
                   break; 
                }
            }

            if(!found_num){
                return sum;
            }
            sum++;
        }
    }
}