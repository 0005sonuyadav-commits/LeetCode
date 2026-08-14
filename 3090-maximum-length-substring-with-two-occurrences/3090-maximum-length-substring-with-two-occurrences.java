class Solution {
    public int maximumLengthSubstring(String s) {
        int left =0;
        int maxlength =0;
        int freq[] = new int[26];
        for(int right=0;right<s.length();right++){
            char ch = s.charAt(right);
            freq[ch-'a']++;

            while(freq[ch-'a']>2){
                freq[s.charAt(left) -'a']--;
                left++;
            }

            maxlength = Math.max(maxlength , right-left+1);
        }
        return maxlength;
    }
}