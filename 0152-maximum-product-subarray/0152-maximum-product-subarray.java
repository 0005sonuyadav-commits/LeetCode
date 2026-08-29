class Solution {
    public int maxProduct(int[] nums) {
        int product=Integer.MIN_VALUE;
        for(int start=0;start<nums.length;start++){
            int currproduct=1;
            for(int end=start;end<nums.length;end++){
                currproduct *= nums[end];
                if(product<currproduct){
                    product = currproduct;
                }
            }
        }
        return product;
    }
}