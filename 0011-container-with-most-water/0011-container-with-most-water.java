class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int area = Integer.MIN_VALUE;

        while(left<right){
            
            int currarea = (right-left) * Math.min(height[left], height[right]);

            if(currarea > area){
                area = currarea;
            }
            if(height[left]<height[right]){
                left++;
            }else if(height[left]>height[right]){
                right--;
            }else{
                left++;
                right--;
            }
           
        }
        return area;
    }
}