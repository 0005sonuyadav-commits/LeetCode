class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        for(int num:nums){
            set.add(num);
        }

        for(int i=1;i<=set.size()+1;i++){
            int value =i*k;
            if(!set.contains(value)){
                return value;
               
            }
        }
        return k*2;
    }
}