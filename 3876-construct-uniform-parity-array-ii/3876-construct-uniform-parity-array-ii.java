class Solution {
    public boolean uniformArray(int[] nums1) {
        int minval = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for(int num:nums1){
            minval = Math.min(minval , num);
            if(num%2 !=0){
                hasOdd =  true;
            }

        }

        if(minval % 2 !=0){
            return true;
        }
        return !hasOdd;
    }
}