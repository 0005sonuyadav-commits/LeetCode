import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Character> opStack = new Stack<>();
        Stack<Set<String>> valStack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (c == '{') {
                // Check if implicit concatenation is needed with previous item
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    opStack.push('*'); // '*' denotes concatenation
                }
                opStack.push('{');
            } else if (c == ',') {
                // Process higher precedence operations (concatenation '*') before comma
                while (!opStack.isEmpty() && opStack.peek() == '*') {
                    evaluateTop(opStack, valStack);
                }
                opStack.push(',');
            } else if (c == '}') {
                // Evaluate inside braces until we reach '{'
                while (!opStack.isEmpty() && opStack.peek() != '{') {
                    evaluateTop(opStack, valStack);
                }
                opStack.pop(); // Pop '{'
            } else {
                // Lowercase letter
                if (i > 0 && (expression.charAt(i - 1) == '}' || Character.isLetter(expression.charAt(i - 1)))) {
                    opStack.push('*');
                }
                
                Set<String> set = new HashSet<>();
                set.add(String.valueOf(c));
                valStack.push(set);
            }
        }
        
        // Evaluate remaining operations in the stack
        while (!opStack.isEmpty()) {
            evaluateTop(opStack, valStack);
        }
        
        // Convert top set to sorted list
        List<String> result = new ArrayList<>(valStack.pop());
        Collections.sort(result);
        return result;
    }
    
    private void evaluateTop(Stack<Character> opStack, Stack<Set<String>> valStack) {
        char op = opStack.pop();
        Set<String> right = valStack.pop();
        Set<String> left = valStack.pop();
        
        Set<String> res = new HashSet<>();
        
        if (op == '*') { // Concatenation
            for (String l : left) {
                for (String r : right) {
                    res.add(l + r);
                }
            }
        } else if (op == ',') { // Union
            res.addAll(left);
            res.addAll(right);
        }
        
        valStack.push(res);
    }
}