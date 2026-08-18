class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if(k == 1){
            HashMap<Integer,Integer> freq = new HashMap<>();
            for(int num:nums){
                freq.put(num , freq.getOrDefault(num,0)+1);
            }
            int max=-1;
            for(int num:nums){
                if(freq.get(num) ==1){
                    max = Math.max(max,num);
                }

            }
            return max;
        }

        if(k == n){
            int max = nums[0];
            for(int i=0;i<n;i++){
                if(nums[i]>max){
                    max = nums[i];
                }
            }
            return max;
        }

        if(1 <k && k<n){
            Map<Integer,Integer> freq = new HashMap<>();
            for(int num :nums){
                freq.put(num , freq.getOrDefault(num,0)+1);
            }

            int ans =-1;

            if(freq.get(nums[0])==1){
                ans = Math.max(ans , nums[0]);
            }

            if(freq.get(nums[n-1]) == 1){
                ans = Math.max(ans,nums[n-1]);
            }
            return ans;
        }
        return -1;
    }
}