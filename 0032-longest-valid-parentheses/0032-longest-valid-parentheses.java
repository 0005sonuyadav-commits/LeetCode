class Solution {
    public int longestValidParentheses(String s) {
        int n=s.length();
        
        Stack<Integer> c = new Stack<>();
        c.push(-1);
        int maxlen = 0;
        for(int i=0;i<n;i++){
            char ch = s.charAt(i);
            if(ch=='('){
                c.push(i);
            }else{
                c.pop();
                if(c.isEmpty()){
                    c.push(i);
                }
                else{
                    maxlen = Math.max(maxlen , i-c.peek());
                }
            }
        }
        return maxlen;
    }
}