class Solution {
    public boolean uniformArray(int[] nums1) {

        int n=nums1.length;
        int nums2[] = new int[n];
        int k=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(nums1[i]%2==0){
                    nums2[k++]=nums1[i];
                }else{
                    nums2[k++] = nums1[i]-nums1[j];
                }
            }
            while(k<nums2.length){
                if(nums2[k]%2==0){
                    return true;
                }else if(nums2[k]%2 !=0){
                    return true;
                }
                k++;
            }
        }
        return false;




        // if(nums1[0]%2==0 || nums2[0]%2 !=0){
        //     return true;
        // }
        // for(int i=0;i<n;i++){
        //     if(nums1[i]%2==0){
        //         return true;
        //     }
        // }return false;
    }
}