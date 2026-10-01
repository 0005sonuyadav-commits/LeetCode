class Solution {
    public boolean isValid(String s) {
        Stack<Character> c = new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch= s.charAt(i);
            if(ch=='(' || ch=='{' || ch=='['){
                c.push(ch);
            }else{
                if(c.isEmpty()){
                    return false;
                }
            
            if(c.peek()=='(' &&  ch==')' || c.peek()=='{' && ch=='}' || c.peek()=='[' && ch==']'){
                c.pop();
            }else{
                return false;
            }

            }
        }  return c.isEmpty();
    }  
            
}