import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // 1. Build a HashMap for O(1) key lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        StringBuilder keyBuilder = new StringBuilder();
        boolean inBracket = false;
        
        // 2. Parse the string character by character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                inBracket = true;
            } else if (ch == ')') {
                inBracket = false;
                String key = keyBuilder.toString();
                // Replace bracket contents with value or '?'
                result.append(map.getOrDefault(key, "?"));
                keyBuilder.setLength(0); // Reset for next key
            } else {
                if (inBracket) {
                    keyBuilder.append(ch);
                } else {
                    result.append(ch);
                }
            }
        }
        
        return result.toString();
    }
}