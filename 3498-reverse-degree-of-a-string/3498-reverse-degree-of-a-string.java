class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            int reversepostion = 'z'- ch + 1;
            sum = sum+reversepostion *(i+1);
        }
        return sum;
    }
}